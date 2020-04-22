
package winterwell.markdown.editors;

import java.io.File;
import java.net.URI;

import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IPathEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.MultiPageEditorPart;

import winterwell.markdown.pagemodel.MarkdownPage;


/**
 * An example showing how to create a multi-page editor. This example has 2
 * pages:
 * <ul>
 * <li>page 0 contains a nested text editor.
 * <li>page 2 shows the words in page 0 in sorted order
 * </ul>
 */
public class MultiPageEditor extends MultiPageEditorPart implements IResourceChangeListener {

    /** The text editor used in page 0. */
    private MarkdownEditor editor;

    /** The text widget used in page 1. */
    private Browser viewer;

    /**
     * Creates a multi-page editor example.
     */
    public MultiPageEditor() {
        super();
        ResourcesPlugin.getWorkspace().addResourceChangeListener(this);
    }

    /**
     * Creates page 0 of the multi-page editor, which contains a text editor.
     */
    void createPage0() {
        try {
            editor = new MarkdownEditor();
            int index = addPage(editor, getEditorInput());
            setPageText(index, "Markdown Source");
            setPartName(editor.getTitle());
        } catch (PartInitException e) {
            ErrorDialog.openError(getSite().getShell(), "Error creating nested text editor", null, e.getStatus());
        }
    }

    /**
     * Creates page 1 of the multi-page editor, which shows the sorted text.
     */
    void createPage1() {
        viewer = new Browser(getContainer(), SWT.MULTI); // | SWT.H_SCROLL | SWT.V_SCROLL

        int index = addPage(viewer);
        setPageText(index, "Preview");
    }

    /**
     * Creates the pages of the multi-page editor.
     */
    protected void createPages() {
        createPage0();
        createPage1();
    }

    /**
     * The <code>MultiPageEditorPart</code> implementation of this
     * <code>IWorkbenchPart</code> method disposes all nested editors.
     * Subclasses may extend.
     */
    public void dispose() {
        ResourcesPlugin.getWorkspace().removeResourceChangeListener(this);
        super.dispose();
    }

    /**
     * Saves the multi-page editor's document.
     */
    public void doSave(IProgressMonitor monitor) {
        getEditor(0).doSave(monitor);
    }

    /**
     * Saves the multi-page editor's document as another file. Also updates the
     * text for page 0's tab, and updates this multi-page editor's input to
     * correspond to the nested editor's.
     */
    public void doSaveAs() {
        IEditorPart editor = getEditor(0);
        editor.doSaveAs();
        setPageText(0, editor.getTitle());
        setInput(editor.getEditorInput());
    }

    /**
     * The <code>MultiPageEditorExample</code> implementation of this method
     * checks that the input is an instance of <code>IFileEditorInput</code>.
     */
    public void init(IEditorSite site, IEditorInput editorInput) throws PartInitException {
//        if (!(editorInput instanceof IFileEditorInput))
//            throw new PartInitException("Invalid Input: Must be IFileEditorInput");
        super.init(site, editorInput);
    }

    /*
     * (non-Javadoc) Method declared on IEditorPart.
     */
    public boolean isSaveAsAllowed() {
        return true;
    }

    /**
     * Calculates the contents of page 1 when the it is activated.
     */
    protected void pageChange(int newPageIndex) {
        super.pageChange(newPageIndex);
        if (newPageIndex == 1) {
            update();
        }
    }

    /**
     * Closes all project files on project close.
     */
    public void resourceChanged(final IResourceChangeEvent event) {
        if (event.getType() == IResourceChangeEvent.PRE_CLOSE) {
            Display.getDefault().asyncExec(() -> {
                IWorkbenchPage[] pages = getSite().getWorkbenchWindow().getPages();
                for (int i = 0; i < pages.length; i++) {
//                    if (((FileEditorInput) editor.getEditorInput()).getFile().getProject().equals(event.getResource())) {
                        IEditorPart editorPart = pages[i].findEditor(editor.getEditorInput());
                        pages[i].closeEditor(editorPart, true);
//                    }
                }
            });
        }
    }

    public void update() {
        if (viewer == null)
            return;
        try {
            MarkdownPage page = editor.getMarkdownPage();
            String html = page.html();
            html = addBaseURL(editor, html);
            viewer.setText(html);
        } catch (Exception ex) {
            // Smother
            System.out.println(ex);

            if (viewer != null && !viewer.isDisposed())
                viewer.setText(ex.getMessage());
        }
    }

    /**
     * Adjust the URL base to be the file's directory.
     * @param editor
     * @param html
     * @return
     */
    private String addBaseURL(IEditorPart editor, String html) {
        try {
            IPathEditorInput input = (IPathEditorInput) editor.getEditorInput();
            IPath path = input.getPath();
            path = path.removeLastSegments(1);
            File f = path.toFile();
            URI fileURI = f.toURI();
            String css =
                    "tr {" +
                    "  border: 1px solid #c6cbd1;" +
                    "}";
            String html2 = "<html><head><base href='" + fileURI + "' /><style>" + css + "</style></head><body>\r\n" + html + "\r\n</body></html>";
            return html2;
        } catch (Exception ex) {
            return html;
        }
    }
}
