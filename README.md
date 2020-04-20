# Eclipse Markdown Editor Plugin

Github Falvored Markdown (includes Table) enabled markdown editor and previewer (in tabbed pane).

This plugin is very ad-hoc build, contribution is welcome.
This plugin used [flexmark](https://github.com/vsch/flexmark-java) as a rendering engine.

## Usage

![](https://lh3.googleusercontent.com/cipDsqoYygTmTExPWsk3lIIzJ-F8GKfz_cm_g_-Gj52cuCdkKSzFzXZulChHauUslG7YHPHj7CEYwKt_j_kw_ZHnGCmX_sWjzHfV7ENGrLwSsoYQ9Sbr9nH4CKjHa1x3wEjlKHmyLaMDejWDQ4ci34J6TUpXRveGb8BgYQZAa0kqlZ728Yieuxtj2ku8vJm-AwlaGClCxvrh8CjNLQKjhDAOB6XwLzVodZoZz7cCom_trpDwnTlgUlzBK9qo-uG-vvv2dOOVjJPAuBClrnzPmmWIj957Mql0dQYXjubb-s0WWFfgWanpM8LFeMZF6bkLoJCeCeGx06EKSGWabLakMUKuorM1zBfwHOxogxJA0fLzcnmAUum9kB2Ko15-7fGW9xgYnSHLYxJzrrrwm8BPbglNr4ZRYiylyOu2BDHOk2mewfCCDyiKO9G2D_JHR4hAu5bVp_iUopofOH30_ekMn-HEBDn5Lh11Pt-BgwrHy728-cOUSMOkAs30ZkM1mvNY4v0vxAMow19mxm9msQ09W2pmFwucKFpwGoxaTlF2AHhB1HJ26aiAhME_HpdWW6DneTwZ-mEMdMgKsaUgcwZ-juCIyMk7PTkNA6Kbe3A0mof-obDTcuuIJpBk6XjaXly5-iGHzhu_BiKqIszp_fkfxAECWg1rHniD97Egdn4u9tbfuM2_l8PYmmGB=w480-h603-no)
![](https://lh3.googleusercontent.com/C5S90vVLdlVKlv5MhZ3Cw_PUgBfRWM_ERa_Z-k0QhvB3U7lMy6qIhChJ35JN42W7Cu5yGwn-gJ_3U66FoamTlFSDsSB2924FL2pxVwwM5D_GqObnimu-XS-_C9NalidTjDYxHBdB4jQOHTOjp1t30GcMbIQlhLGs_Mo9xOP7-6-V12NEM85gkIsVqIeRg56UNdAgRYP5EX-iEjPA5epreGJUtHrZIo2ST0u4sRgwjHP_fWfi-cjPKuk-H7HnFlsHDZV91QsaDCTOtZiuVT3fyB49wj0wgXE1jFrTUBNLyBxtKF1kPSaRk--Unaye0BGKxQwtYRYQaSWE7hYytfjOXUW5r4ZBOuOe8gnl3qyHC37HwBOWW8-ZLYgNoHd0mU9njOQncPM9XK-dUv3WpfC7PWNKJNvxdzh_-vcLB1cNWrDlLqewKfz8fJQwmYBjMlqpXMO99r_z0yyYf99H0xk_XcyRKWPxife-5HQn36p9x5JR_4IsqRmmlR0Cm52OIyY1Hnybemlntfma6iHPQ21x_94-n1yR4l1dvWvEVlX3WXE29vBmecGsTixcjpH47cxb1KD_x5KJKA7s23ev-UyRSNKbDPf40fU4hXXH_mPBhRZX7R_ngEb9JPDPkw4Xmv99O3q9ZtWiMEuAL-ivWnC5IxdwZJ3Atw4UROuTWjkobWwoXjG70TyyNIAi=w480-h587-no)

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