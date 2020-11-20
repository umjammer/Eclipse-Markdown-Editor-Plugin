# Eclipse Markdown Editor Plugin

Github Falvored Markdown (includes Table) enabled markdown editor and previewer (in tabbed pane).

This plugin is very ad-hoc build, contribution is welcome.
This plugin uses [flexmark](https://github.com/vsch/flexmark-java) as a rendering engine.

## Usage

![](https://lh3.googleusercontent.com/h6sBMwCYRs3Cx49IDR8OPWpjCeXi9k0C5c28Z4FQAEhYgs3nE8F44XePI_vCq15mgEf0ym5eTIOq4GEAGA=w360-h440-rw)
![](https://lh3.googleusercontent.com/vqf4DNm9Ww2d1P5_onVjQEBNBYuhwRoRXs5EVU6hM2qfd8uL4DcVqvUgc89q79XQm0NKlL8CjRUka9bo0Q=w350-h440-rw)

Note that for HTML preview OS the internal browser is used, e.g. Internet Explorer on Windows; if you use Ubuntu or other Linux distros, we recommend the WebKit browser; see:

- <http://www.eclipse.org/swt/faq.php#browserlinuxrcp>
- <http://www.eclipse.org/swt/faq.php#browserlinux>
- <http://tronprog.blogspot.de/2012/05/eclipse-internal-web-browser-in-kubuntu.html>

## Eclipse Dev Details

You need Eclipse with PDE, e.g. Eclipse Standard



![](overview.png)

Main Editor class `winterwell.markdown.editors.MarkdownEditor` defined as

      <editor
            name="Markdown Editor"
            extensions="txt,md"
            icon="icons/notepad.gif"
            contributorClass="winterwell.markdown.editors.ActionBarContributor"
            class="winterwell.markdown.editors.MarkdownEditor"
            id="winterwell.markdown.editors.MarkdownEditor">
      </editor>

### Build

	mvn package
      
then check `site\target` directory for update site archive `markdown.editor.site-x.x.x.zip` and p2 repository.
Use Help -> Install New Software... -> Add... -> Archive to istall from .zip file.

Increase version

	mvn -Dtycho.mode=maven org.eclipse.tycho:tycho-versions-plugin:set-version -DnewVersion=1.2.0-SNAPSHOT

### History

- 1.0
- 1.1 (24 Feb 2014) by Telmo Brugnara @tbrugz #40
  - Rich color preferences #35 #37
- 1.2 (Jan 2015) by Olivier Martin @oliviermartin #52
  - Update preview when the file is saved #48
  - MultiMarkdown metadata #49
  - GitHub code blocks #50
  - detecting links #51
  - open GFM View from Markdown View #53

## TODO

 * clean up code
 * github flavored css
 * ~~modify main icon 16x16~~
 * ~~change filename to main tab~~
   * when the same name and the different project
 * eliminate gfm checkbox on preferences pane
 * eliminate markdown view
 * ~~version~~
 * got blank when browser back (preview pane)
 * https://github.com/dschaefer/swt-cef
