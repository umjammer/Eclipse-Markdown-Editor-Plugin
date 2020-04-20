
package winterwell.markdown.editors;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.part.MultiPageEditorActionBarContributor;
import org.eclipse.ui.texteditor.ITextEditor;

import winterwell.markdown.views.MarkdownPreview;


/**
 * Manages the installation/deinstallation of global actions for multi-page
 * editors. Responsible for the redirection of global actions to the active
 * editor. Multi-page contributor replaces the contributors for the individual
 * editors in the multi-page editor.
 */
public class MultiPageEditorContributor extends MultiPageEditorActionBarContributor {
    private static IEditorPart activeEditorPart;

    public static IEditorPart getActiveEditor() {
        return activeEditorPart;
    }

    /**
     * Returns the action registed with the given text editor.
     *
     * @return IAction or null if editor is null.
     */
    protected IAction getAction(ITextEditor editor, String actionID) {
        return (editor == null ? null : editor.getAction(actionID));
    }

    /*
     * (non-JavaDoc) Method declared in
     * AbstractMultiPageEditorActionBarContributor.
     */
    public void setActivePage(IEditorPart part) {
        if (activeEditorPart == part)
            return;

        activeEditorPart = part;

        IActionBars actionBars = getActionBars();
        if (actionBars != null) {
//          todo actionBars.setGlobalActionHandler(ActionFactory.PRINT.getId(), print);
//          actionBars.updateActionBars();
        }

        if (MarkdownPreview.preview != null) {
            MarkdownPreview.preview.update();
        }
    }

    public void contributeToMenu(IMenuManager manager) {
        super.contributeToMenu(manager);
        // Add format action
        IMenuManager edit = manager.findMenuUsingPath("edit");
        if (edit != null) {
            edit.add(new FormatAction());
        }
        // Add Export action
        IMenuManager file = manager.findMenuUsingPath("file");
        if (file != null) {
            file.appendToGroup("import.ext", new ExportHTMLAction());
        }
    }
}
