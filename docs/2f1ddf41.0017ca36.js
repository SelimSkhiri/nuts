(window.webpackJsonp=window.webpackJsonp||[]).push([[15],{114:function(e,t,n){"use strict";n.d(t,"a",(function(){return u})),n.d(t,"b",(function(){return d}));var a=n(0),r=n.n(a);function i(e,t,n){return t in e?Object.defineProperty(e,t,{value:n,enumerable:!0,configurable:!0,writable:!0}):e[t]=n,e}function l(e,t){var n=Object.keys(e);if(Object.getOwnPropertySymbols){var a=Object.getOwnPropertySymbols(e);t&&(a=a.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),n.push.apply(n,a)}return n}function o(e){for(var t=1;t<arguments.length;t++){var n=null!=arguments[t]?arguments[t]:{};t%2?l(Object(n),!0).forEach((function(t){i(e,t,n[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(n)):l(Object(n)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(n,t))}))}return e}function s(e,t){if(null==e)return{};var n,a,r=function(e,t){if(null==e)return{};var n,a,r={},i=Object.keys(e);for(a=0;a<i.length;a++)n=i[a],t.indexOf(n)>=0||(r[n]=e[n]);return r}(e,t);if(Object.getOwnPropertySymbols){var i=Object.getOwnPropertySymbols(e);for(a=0;a<i.length;a++)n=i[a],t.indexOf(n)>=0||Object.prototype.propertyIsEnumerable.call(e,n)&&(r[n]=e[n])}return r}var c=r.a.createContext({}),p=function(e){var t=r.a.useContext(c),n=t;return e&&(n="function"==typeof e?e(t):o(o({},t),e)),n},u=function(e){var t=p(e.components);return r.a.createElement(c.Provider,{value:t},e.children)},b={inlineCode:"code",wrapper:function(e){var t=e.children;return r.a.createElement(r.a.Fragment,{},t)}},m=r.a.forwardRef((function(e,t){var n=e.components,a=e.mdxType,i=e.originalType,l=e.parentName,c=s(e,["components","mdxType","originalType","parentName"]),u=p(n),m=a,d=u["".concat(l,".").concat(m)]||u[m]||b[m]||i;return n?r.a.createElement(d,o(o({ref:t},c),{},{components:n})):r.a.createElement(d,o({ref:t},c))}));function d(e,t){var n=arguments,a=t&&t.mdxType;if("string"==typeof e||a){var i=n.length,l=new Array(i);l[0]=m;var o={};for(var s in t)hasOwnProperty.call(t,s)&&(o[s]=t[s]);o.originalType=e,o.mdxType="string"==typeof e?e:a,l[1]=o;for(var c=2;c<i;c++)l[c]=n[c];return r.a.createElement.apply(null,l)}return r.a.createElement.apply(null,n)}m.displayName="MDXCreateElement"},69:function(e,t,n){"use strict";n.r(t),n.d(t,"frontMatter",(function(){return l})),n.d(t,"metadata",(function(){return o})),n.d(t,"rightToc",(function(){return s})),n.d(t,"default",(function(){return p}));var a=n(2),r=n(6),i=(n(0),n(114)),l={id:"changelog",title:"Change Log",sidebar_label:"Change Log"},o={unversionedId:"info/changelog",id:"info/changelog",isDocsHomePage:!1,title:"Change Log",description:"`",source:"@site/docs/info/change-log.md",permalink:"/nuts/docs/info/changelog",editUrl:"https://github.com/facebook/docusaurus/edit/master/website/docs/info/change-log.md",sidebar_label:"Change Log",sidebar:"someSidebar",previous:{title:"Your first Nuts Application",permalink:"/nuts/docs/dev/firstNutsApp"},next:{title:"Frequently Asked Questions",permalink:"/nuts/docs/info/faq"}},s=[{value:"nuts 0.8.1.0",id:"nuts-0810",children:[]},{value:"nuts 0.8.0.0",id:"nuts-0800",children:[]},{value:"nuts 0.7.2.0",id:"nuts-0720",children:[]},{value:"nuts 0.7.1.0",id:"nuts-0710",children:[]},{value:"nuts 0.7.0.0",id:"nuts-0700",children:[]},{value:"nuts 0.6.0.0",id:"nuts-0600",children:[]},{value:"nuts 0.5.8.0",id:"nuts-0580",children:[]},{value:"nuts 0.5.7.0",id:"nuts-0570",children:[]},{value:"nuts 0.5.6.0",id:"nuts-0560",children:[]},{value:"nuts 0.5.5.0",id:"nuts-0550",children:[]},{value:"nuts 0.5.4.0 Change Log",id:"nuts-0540-change-log",children:[]},{value:"nuts 0.5.3.0 Change Log",id:"nuts-0530-change-log",children:[]},{value:"nuts 0.5.2.0 Change Log",id:"nuts-0520-change-log",children:[]},{value:"nuts 0.5.1.0 Change Log",id:"nuts-0510-change-log",children:[]},{value:"nuts 0.5.0.0 Change Log",id:"nuts-0500-change-log",children:[]}],c={rightToc:s};function p(e){var t=e.components,n=Object(r.a)(e,["components"]);return Object(i.b)("wrapper",Object(a.a)({},c,n,{components:t,mdxType:"MDXLayout"}),Object(i.b)("pre",null,Object(i.b)("code",Object(a.a)({parentName:"pre"},{}),"     __        __    \n  /\\ \\ \\ _  __/ /______\n /  \\/ / / / / __/ ___/\n/ /\\  / /_/ / /_(__  )\n\\_\\ \\/\\__,_/\\__/____/    version v0.8.1\n")),Object(i.b)("p",null,"View Official releases ",Object(i.b)("a",Object(a.a)({parentName:"p"},{href:"https://github.com/thevpc/nuts/releases"}),"here")," :\nStarred releases are most stable ones."),Object(i.b)("h3",{id:"nuts-0810"},"nuts 0.8.1.0"),Object(i.b)("ul",null,Object(i.b)("li",{parentName:"ul"},Object(i.b)("inlineCode",{parentName:"li"},"2020/11/22 \tnuts 0.8.1.0 (*)")," released ",Object(i.b)("a",Object(a.a)({parentName:"li"},{href:"https://repo.maven.apache.org/maven2/net/thevpc/nuts/nuts/0.8.1/nuts-0.8.1.jar"}),"download nuts-0.8.1.jar")),Object(i.b)("li",{parentName:"ul"},"WARNING: API has evolved with some incompatibilities with previous versions  "),Object(i.b)("li",{parentName:"ul"},"ADDED  : ","[ntalk-agent]"," new modules nlib-talk-agent (library) and ntalk-agent (application using the library) that enable client to client communication.\nnlib-talk-agent is a broker that helps communication between nuts components with minimum overhead.\nnlib-talk-agent enables one workspace to talk with any other workspace without having to create one server socket for each workspace.\nIt also enables singleton per location implementation "),Object(i.b)("li",{parentName:"ul"},"CHANGED: ","[runtime]"," revamped Nuts Text Format to support with simplified syntax but more verbose styles.\nNow supports #), ##), ###) and so on as Title Node.\nIt supports as well the common markdown 'code' format with anti-quotes such as\n",Object(i.b)("inlineCode",{parentName:"li"},"java code goes here..."),"\nOther supported examples are:\n",Object(i.b)("inlineCode",{parentName:"li"},"sh some command..."),Object(i.b)("inlineCode",{parentName:"li"},"error error message..."),Object(i.b)("inlineCode",{parentName:"li"},"kw someKeyword")),Object(i.b)("li",{parentName:"ul"},"CHANGED: ","[runtime]",' help files now have extensions ".ntf" (for nuts text format) instead of ".help"'),Object(i.b)("li",{parentName:"ul"},"FIXED  : ","[api]"," extension support (for JLine)"),Object(i.b)("li",{parentName:"ul"},"ADDED  : ","[njob]"," added --help sub-command"),Object(i.b)("li",{parentName:"ul"},"FIXED  : ","[nsh]","  fixed multiple inconsistencies and implemented a brand new parser"),Object(i.b)("li",{parentName:"ul"},"REMOVED: ","[docusaurus-to-ascidoctor]"," tool fully removed as replaced by a more mature ndocusaurus"),Object(i.b)("li",{parentName:"ul"},"REMOVED: ","[ndi]",", removed project, merged into nadmin"),Object(i.b)("li",{parentName:"ul"},"REMOVED: ","[nded]",", removed project, temporarily code added to nadmin, needs to be refactored")),Object(i.b)("h3",{id:"nuts-0800"},"nuts 0.8.0.0"),Object(i.b)("ul",null,Object(i.b)("li",{parentName:"ul"},Object(i.b)("inlineCode",{parentName:"li"},"2020/11/8? \tnuts 0.8.0.0 (*)")," released ",Object(i.b)("a",Object(a.a)({parentName:"li"},{href:"https://repo.maven.apache.org/maven2/net/thevpc/nuts/nuts/0.8.0/nuts-0.8.0.jar"}),"download nuts-0.8.0.jar")),Object(i.b)("li",{parentName:"ul"},"WARNING: this is the first version to be deployed to maven central. previous versions will no longer be supported"),Object(i.b)("li",{parentName:"ul"},"WARNING: this is a ",Object(i.b)("strong",{parentName:"li"},"major version"),", API has evolved with multiple incompatibilities with previous versions  "),Object(i.b)("li",{parentName:"ul"},"WARNING: The OSS License has changed from GPL3 to the more permessive Apache Licence v2.0"),Object(i.b)("li",{parentName:"ul"},"CHANGED: changed packages from net.vpc to net.thevpc (required for central to be aligned with website)"),Object(i.b)("li",{parentName:"ul"},"CHANGED: removed support for vpc-public-maven and vpc-public-nuts"),Object(i.b)("li",{parentName:"ul"},"CHANGED: ",Object(i.b)("inlineCode",{parentName:"li"},"nuts -Z")," will update ",Object(i.b)("inlineCode",{parentName:"li"},".bashrc")," file and switch back to default workspace"),Object(i.b)("li",{parentName:"ul"},"ADDED  : when a dependency is missing it will be shown in the error message"),Object(i.b)("li",{parentName:"ul"},"ADDED  : nuts commandline argument --N (--expire) to force reloading invoked artifacts (expire fetched jars). a related NutsSession.expireTime is introduced to force reinstall of any launched application and it dependencies, example: ",Object(i.b)("inlineCode",{parentName:"li"},"nuts -N ndi")),Object(i.b)("li",{parentName:"ul"},"ADDED  : install --strategy=install|reinstall|require|repair introduced to select install strategy (or sub command)"),Object(i.b)("li",{parentName:"ul"},"ADDED  : NutsInput & NutsOutput to help considering reusable sources/targets"),Object(i.b)("li",{parentName:"ul"},"ADDED  : nuts commandline argument --skip-errors  to ignore unsupported commandline args"),Object(i.b)("li",{parentName:"ul"},"ADDED  : new toolbox njob, to track service jobs (how many hours you are working on each service project)"),Object(i.b)("li",{parentName:"ul"},"ADDED  : new next-term, to support jline console extension into nuts"),Object(i.b)("li",{parentName:"ul"},"ADDED  : workspace.str() to create NutsStringBuilder"),Object(i.b)("li",{parentName:"ul"},"ADDED  : 'switch' command in ndi to support switching from one workspace to another. example : ",Object(i.b)("inlineCode",{parentName:"li"},"ndi switch -w other-workspace -a 0.8.0"))),Object(i.b)("h3",{id:"nuts-0720"},"nuts 0.7.2.0"),Object(i.b)("p",null,"WARNING: this version is not deployed to maven-central"),Object(i.b)("ul",null,Object(i.b)("li",{parentName:"ul"},Object(i.b)("inlineCode",{parentName:"li"},"2020/09/23 \tnuts 0.7.2.0 (*)")," released ",Object(i.b)("a",Object(a.a)({parentName:"li"},{href:"https://github.com/thevpc/vpc-public-maven/raw/master/net/vpc/app/nuts/nuts/0.7.2/nuts-0.7.2.jar"}),"download nuts-0.7.2.jar")," "),Object(i.b)("li",{parentName:"ul"},"FIXED  : execute non installed artifacts sometimes do not ask for confirmation"),Object(i.b)("li",{parentName:"ul"},"ADDED  : NutsCommandLineProcessor.prepare/exec/autoComplete"),Object(i.b)("li",{parentName:"ul"},"ADDED  : NutsApplicationContext.processCommandLine(cmdLine)"),Object(i.b)("li",{parentName:"ul"},"ADDED  : NutsApplicationContext.configureLast(cmdLine)"),Object(i.b)("li",{parentName:"ul"},"RENAMED: feenoo renamed to ncode"),Object(i.b)("li",{parentName:"ul"},"ADDED  : Docusaurus Website"),Object(i.b)("li",{parentName:"ul"},"ADDED  : new toolbox ndocusaurus : Docusaurus Website templating")),Object(i.b)("h3",{id:"nuts-0710"},"nuts 0.7.1.0"),Object(i.b)("p",null,"WARNING: this version is not deployed to maven-central"),Object(i.b)("ul",null,Object(i.b)("li",{parentName:"ul"},Object(i.b)("inlineCode",{parentName:"li"},"2020/09/14 \tnuts 0.7.1.0 (*)")," released ",Object(i.b)("a",Object(a.a)({parentName:"li"},{href:"https://github.com/thevpc/vpc-public-maven/raw/master/net/vpc/app/nuts/nuts/0.7.1/nuts-0.7.1.jar"}),"download nuts-0.7.1.jar")),Object(i.b)("li",{parentName:"ul"},"FIXED  : reset stdout line when calling external processes"),Object(i.b)("li",{parentName:"ul"},"FIXED  : fixed several display issues.")),Object(i.b)("h3",{id:"nuts-0700"},"nuts 0.7.0.0"),Object(i.b)("p",null,"WARNING: this version is not deployed to maven-central"),Object(i.b)("ul",null,Object(i.b)("li",{parentName:"ul"},Object(i.b)("inlineCode",{parentName:"li"},"2020/07/26 \tnuts 0.7.0.0 (*)")," released ",Object(i.b)("a",Object(a.a)({parentName:"li"},{href:"https://github.com/thevpc/vpc-public-maven/raw/master/net/vpc/app/nuts/nuts/0.7.0/nuts-0.7.0.jar"}),"download nuts-0.7.0.jar")),Object(i.b)("li",{parentName:"ul"},"ADDED  : NutsApplicationContext.processCommandLine(c)"),Object(i.b)("li",{parentName:"ul"},"ADDED  : NutsWorkspaceCommand.copySession()"),Object(i.b)("li",{parentName:"ul"},"RENAMED: derby renamed to nderby"),Object(i.b)("li",{parentName:"ul"},"RENAMED: mysql renamed to nmysql"),Object(i.b)("li",{parentName:"ul"},"RENAMED: tomcat renamed to ntomcat"),Object(i.b)("li",{parentName:"ul"},"RENAMED: mvn renamed to nmvn")),Object(i.b)("h3",{id:"nuts-0600"},"nuts 0.6.0.0"),Object(i.b)("p",null,"WARNING: this version is not deployed to maven-central"),Object(i.b)("ul",null,Object(i.b)("li",{parentName:"ul"},Object(i.b)("inlineCode",{parentName:"li"},"2020/01/15 \tnuts 0.6.0.0 (*)")," released ",Object(i.b)("a",Object(a.a)({parentName:"li"},{href:"https://github.com/thevpc/vpc-public-maven/raw/master/net/vpc/app/nuts/nuts/0.6.0/nuts-0.6.0.jar"}),"download nuts-0.6.0.jar")," "),Object(i.b)("li",{parentName:"ul"},"CHANGED  : config file format changed"),Object(i.b)("li",{parentName:"ul"},"CHANGED  : now installed packages are stored in 'installed' meta repository"),Object(i.b)("li",{parentName:"ul"},"CHANGED  : alias files have extension changed form ",Object(i.b)("em",{parentName:"li"},".njc to "),".cmd-alias.json"),Object(i.b)("li",{parentName:"ul"},"CHANGED  : now nuts looks for system env variable NUTS_WORKSPACE for default workspace location"),Object(i.b)("li",{parentName:"ul"},"CHANGED  : api and runtime are installed by default"),Object(i.b)("li",{parentName:"ul"},"CHANGED  : now distinguishes between installed primary and installed dependencies packages."),Object(i.b)("li",{parentName:"ul"},"ADDED    : support for ROOT_CMD execution (SYSCALL was renamed USER_CMD)"),Object(i.b)("li",{parentName:"ul"},"ADDED    : support for Interrupting Copy"),Object(i.b)("li",{parentName:"ul"},"ADDED    : support to ps (list processes)"),Object(i.b)("li",{parentName:"ul"},"ADDED    : support progress options"),Object(i.b)("li",{parentName:"ul"},"CHANGED  : worky, searches now for modified deployments with same version but different content"),Object(i.b)("li",{parentName:"ul"},"FIXED    : encoding problem with json/xml"),Object(i.b)("li",{parentName:"ul"},"REMOVED  : NutsRepositorySession")),Object(i.b)("h3",{id:"nuts-0580"},"nuts 0.5.8.0"),Object(i.b)("p",null,"WARNING: this version is not deployed to maven-central"),Object(i.b)("ul",null,Object(i.b)("li",{parentName:"ul"},Object(i.b)("inlineCode",{parentName:"li"},"2019/09/02 \tnuts 0.5.8.0 (*)")," released ",Object(i.b)("a",Object(a.a)({parentName:"li"},{href:"https://github.com/thevpc/vpc-public-maven/raw/master/net/vpc/app/nuts/nuts/0.5.7/nuts-0.5.7.jar"}),"download nuts-0.5.8.jar")," "),Object(i.b)("li",{parentName:"ul"},"ADDED    : support for Custom Monitor in Copy Command"),Object(i.b)("li",{parentName:"ul"},"ADDED    : support to javaw for windows (exec  command supports --javaw or --win flag)"),Object(i.b)("li",{parentName:"ul"},"ADDED    : support to workspace custom logging (with support for colouring)"),Object(i.b)("li",{parentName:"ul"},"ADDED    : support to userProperties per repository"),Object(i.b)("li",{parentName:"ul"},"ADDED    : NutsString and NutsStringFormat to support 'Nuts Stream Format'"),Object(i.b)("li",{parentName:"ul"},"ADDED    : NutsWarkspaceAware to support initialize/dispose of NutsComponents"),Object(i.b)("li",{parentName:"ul"},"ADDED    : I/O Delete action"),Object(i.b)("li",{parentName:"ul"},"ADDED    : I/O Lock action"),Object(i.b)("li",{parentName:"ul"},"ADDED    : I/O Compress and Uncompress actions"),Object(i.b)("li",{parentName:"ul"},"CHANGE   : now if a command to execute ends with '!', we will force searching in installed only."),Object(i.b)("li",{parentName:"ul"},"CHANGE   : removed install/uninstall in Terminal, replaced by NutsWarkspaceAware")),Object(i.b)("h3",{id:"nuts-0570"},"nuts 0.5.7.0"),Object(i.b)("p",null,"WARNING: this version is not deployed to maven-central"),Object(i.b)("ul",null,Object(i.b)("li",{parentName:"ul"},Object(i.b)("inlineCode",{parentName:"li"},"2019/07/23 \tnuts 0.5.7.0 (*)")," released ",Object(i.b)("a",Object(a.a)({parentName:"li"},{href:"https://github.com/thevpc/vpc-public-maven/raw/master/net/vpc/app/nuts/nuts/0.5.7/nuts-0.5.7.jar"}),"download nuts-0.5.7.jar")," "),Object(i.b)("li",{parentName:"ul"},"ADDED    : support to Windows (Tested on Win 7) and MacOS(Not Tested) ofr Desktop Integration"),Object(i.b)("li",{parentName:"ul"},"ADDED    : added session and Nuts(Add/Update/Remove)Options where applicable"),Object(i.b)("li",{parentName:"ul"},"ADDED    : Initial support for uri based workspaces"),Object(i.b)("li",{parentName:"ul"},"ADDED    : --dry option to help dry-run commands (test execution without side effects)"),Object(i.b)("li",{parentName:"ul"},"ADDED    : NutsApplication getShared*Folder() method for configuration shared between versions"),Object(i.b)("li",{parentName:"ul"},"ADDED    : flags (in Definition and search) : api,runtime,extension,companion"),Object(i.b)("li",{parentName:"ul"},"CHANGED  : Improved compatibility with Maven"),Object(i.b)("li",{parentName:"ul"},"CHANGED  : Improved Documentation (still to much to go though)"),Object(i.b)("li",{parentName:"ul"},"CHANGED  : Changed NutsCommandLine main api to simplify boot time implementations "),Object(i.b)("li",{parentName:"ul"},"CHANGED  : Renamed NutsEffectiveUser->NutsUser "),Object(i.b)("li",{parentName:"ul"},"CHANGED  : Renamed NutsRight->NutsPermission (and all subsequent methods) "),Object(i.b)("li",{parentName:"ul"},"CHANGED  : NutsExtensionInfo->NutsExtensionInformation"),Object(i.b)("li",{parentName:"ul"},"CHANGED  : NutsHttpConnectionFacade->NutsHttpConnection "),Object(i.b)("li",{parentName:"ul"},"CHANGED  : Added java.io.Serializable anchor when applicable"),Object(i.b)("li",{parentName:"ul"},"REMOVED  : NutsDefaultRepositoriesProvider,NutsSingletonClassLoaderProvider,NutsDefaultClassLoaderProvider,NutsWorkspaceSPI "),Object(i.b)("li",{parentName:"ul"},"REMOVED  : NutsRepositoryListener.onInstall(...) "),Object(i.b)("li",{parentName:"ul"},"REMOVED  : 'alternative' concept, and added NutsClassifierMapping so that classifier can be resolved according to env ")),Object(i.b)("h3",{id:"nuts-0560"},"nuts 0.5.6.0"),Object(i.b)("p",null,"WARNING: this version is not deployed to maven-central"),Object(i.b)("ul",null,Object(i.b)("li",{parentName:"ul"},Object(i.b)("inlineCode",{parentName:"li"},"2019/06/23 \tnuts 0.5.6.0")," released ",Object(i.b)("a",Object(a.a)({parentName:"li"},{href:"https://github.com/thevpc/vpc-public-maven/raw/master/net/vpc/app/nuts/nuts/0.5.6/nuts-0.5.6.jar"}),"download nuts-0.5.6.jar")," "),Object(i.b)("li",{parentName:"ul"},"ADDED    : Implements XDG Base Directory Specification"),Object(i.b)("li",{parentName:"ul"},"ADDED    : Added Json Path support"),Object(i.b)("li",{parentName:"ul"},"ADDED    : Added NutsQuestionParser and NutsQuestionFormat "),Object(i.b)("li",{parentName:"ul"},"CHANGED  : Extensions are loaded by boot instead of impl so that one can change default impl behavour"),Object(i.b)("li",{parentName:"ul"},"CHANGED  : All repositories are now cache aware."),Object(i.b)("li",{parentName:"ul"},"CHANGED  : Refactored *Format to extends the very same interface."),Object(i.b)("li",{parentName:"ul"},"CHANGED  : Using to java.time package instead of older Date class"),Object(i.b)("li",{parentName:"ul"},"CHANGED  : Improved Documentation (still to much to go though)"),Object(i.b)("li",{parentName:"ul"},"CHANGED  : Prefer https repository urls"),Object(i.b)("li",{parentName:"ul"},"FIXED    : Fixed several issues"),Object(i.b)("li",{parentName:"ul"},"REMOVED  : ","[CommandLine]"," IMMEDIATE"),Object(i.b)("li",{parentName:"ul"},"REMOVED  : ","[Options]"," --term"),Object(i.b)("li",{parentName:"ul"},"REMOVED  : ","[Extensions]"," add/remove extensions from extension manager (should use install/uninstall commands)")),Object(i.b)("h3",{id:"nuts-0550"},"nuts 0.5.5.0"),Object(i.b)("p",null,"WARNING: this version is not deployed to maven-central"),Object(i.b)("ul",null,Object(i.b)("li",{parentName:"ul"},Object(i.b)("inlineCode",{parentName:"li"},"2019/06/08 \tnuts 0.5.5.0")," released ",Object(i.b)("a",Object(a.a)({parentName:"li"},{href:"https://github.com/thevpc/vpc-public-maven/raw/master/net/vpc/app/nuts/nuts/0.5.5/nuts-0.5.5.jar"}),"download nuts-0.5.5.jar")," "),Object(i.b)("li",{parentName:"ul"},"REMOVED  : Removed Nsh commands Console Deploy Info Install Fetch Uninstall,Push Update Exec Which"),Object(i.b)("li",{parentName:"ul"},"REMOVED  : Removed maven-github repository type support (web API)"),Object(i.b)("li",{parentName:"ul"},"REMOVED  : Removed nuts-cmd-app project dependency. A built-in NutsApplication is included in the api the help simplify extension."),Object(i.b)("li",{parentName:"ul"},"ADDED    : Added support for XML,TABLE and TREE (along with JSON, PROPS and PLAIN) printing format to help automate result parsing"),Object(i.b)("li",{parentName:"ul"},"ADDED    : Added Better api in Nuts IO to handle SHA and MD5"),Object(i.b)("li",{parentName:"ul"},"ADDED    : json and xml nsh commands to help manipulating json and xml in commands outputs"),Object(i.b)("li",{parentName:"ul"},'FIXED    : Fixed fprint issue with "" (empty string)'),Object(i.b)("li",{parentName:"ul"},"FIXED    : Fixed Update indexes/stats command"),Object(i.b)("li",{parentName:"ul"},"FIXED    : When installing nuts, lookup latest core implementation"),Object(i.b)("li",{parentName:"ul"},"CHANGED  : Renamed FindCommand to SearchCommand (and some of their methods too)"),Object(i.b)("li",{parentName:"ul"},"CHANGED  : NutsIdFilter.accept accepts workspace as a second argument"),Object(i.b)("li",{parentName:"ul"},"CHANGED  : Improved Help text"),Object(i.b)("li",{parentName:"ul"},"CHANGED  : Improved Documentation (still to much to go through)"),Object(i.b)("li",{parentName:"ul"},"ADDED    : (nsh) Builtin nsh commands basename and dirname "),Object(i.b)("li",{parentName:"ul"},"CHANGED  : (nsh) Builtin nsh command who renamed to whoami "),Object(i.b)("li",{parentName:"ul"},"REMOVED  : (nfind) Removed nfind companion (the built-in search command is a better replacement)")),Object(i.b)("h3",{id:"nuts-0540-change-log"},"nuts 0.5.4.0 Change Log"),Object(i.b)("p",null,"WARNING: this version is not deployed to maven-central"),Object(i.b)("ul",null,Object(i.b)("li",{parentName:"ul"},Object(i.b)("inlineCode",{parentName:"li"},"2019/04/21 \tnuts 0.5.4.0 (*)")," released ",Object(i.b)("a",Object(a.a)({parentName:"li"},{href:"https://github.com/thevpc/vpc-public-maven/raw/master/net/vpc/app/nuts/nuts/0.5.4/nuts-0.5.4.jar"}),"download nuts-0.5.4.jar"),"\n-Added lucene indexing facility (thanks to the excellent work of nasreddine bac ali)"),Object(i.b)("li",{parentName:"ul"},"Removed dependencies to common,strings, io and utils (utility classes)."),Object(i.b)("li",{parentName:"ul"},"Removed dependencies to asm (bytecode manipulation)."),Object(i.b)("li",{parentName:"ul"},"From Now on only gson and jansi are retained."),Object(i.b)("li",{parentName:"ul"},"Layout changes",Object(i.b)("ul",{parentName:"li"},Object(i.b)("li",{parentName:"ul"},"from now on configuration will be version specific. some migration should be done to import previous configs"),Object(i.b)("li",{parentName:"ul"},"system (global) repo is no more created under the workspace. Only a link to is is registered in nuts-workspace.json"),Object(i.b)("li",{parentName:"ul"},"added MacOs Layout. Help is needed for testing this !"))),Object(i.b)("li",{parentName:"ul"},"Better support for JDK 8+ (New IO,Predicates, Streams, ...)"),Object(i.b)("li",{parentName:"ul"},"Added Comprehensive implementation of Iterator (Stream Like) to better handle result iteration while search is in progress"),Object(i.b)("li",{parentName:"ul"},"Speed improvements"),Object(i.b)("li",{parentName:"ul"},"Added JUnit test battery"),Object(i.b)("li",{parentName:"ul"},"Added support to JSON,PROPS and PLAIN result, implemented in version and info. Should continue implementing in other commands."),Object(i.b)("li",{parentName:"ul"},'Removed --license, --update, --install, ... options, replaced by workspace "internal" commands new concept.'),Object(i.b)("li",{parentName:"ul"},'Workspaces handle several type of executables that will be resolved in that order : "internal command","aliases : aka workspace command aliases", "components",\n"path/unmanaged components" and system/native commands.'),Object(i.b)("li",{parentName:"ul"},"Several Fixes",Object(i.b)("ul",{parentName:"li"},Object(i.b)("li",{parentName:"ul"},"Fixed Problem with Layout"),Object(i.b)("li",{parentName:"ul"},"Fixed Problem coloring (fprint embedded library)"),Object(i.b)("li",{parentName:"ul"},'All System properties now start with "nuts."'),Object(i.b)("li",{parentName:"ul"},'System properties starting with "nuts.export." are exported to children processes'),Object(i.b)("li",{parentName:"ul"},"Added watch dog agains infinite child process creation")))),Object(i.b)("h3",{id:"nuts-0530-change-log"},"nuts 0.5.3.0 Change Log"),Object(i.b)("p",null,"WARNING: this version is not deployed to maven-central"),Object(i.b)("ul",null,Object(i.b)("li",{parentName:"ul"},Object(i.b)("inlineCode",{parentName:"li"},"2019/01/05 \tnuts 0.5.3.0")," released ",Object(i.b)("a",Object(a.a)({parentName:"li"},{href:"https://github.com/thevpc/vpc-public-maven/raw/master/net/vpc/app/nuts/nuts/0.5.3/nuts-0.5.3.jar"}),"download nuts-0.5.3.jar")),Object(i.b)("li",{parentName:"ul"},"(WINDOWS) First support to Windows platform",Object(i.b)("ul",{parentName:"li"},Object(i.b)("li",{parentName:"ul"},"Support for Console coloring on windows"),Object(i.b)("li",{parentName:"ul"},"Storing to AppData","\\","Local and AppData","\\","Roaming folders"),Object(i.b)("li",{parentName:"ul"},"ndi is not yet supported!"))),Object(i.b)("li",{parentName:"ul"},"(LINUX,UNIX) ndi no more stores to ~/bin but instead it updates .bashrc to point to current workspace added a confirmation question."),Object(i.b)("li",{parentName:"ul"},"API Change",Object(i.b)("ul",{parentName:"li"},Object(i.b)("li",{parentName:"ul"},"Moved getStoreRoot from NutsWorkspace to NutsWorkspaceConfigManager"),Object(i.b)("li",{parentName:"ul"},"Added StoreType : CACHE,LIB"),Object(i.b)("li",{parentName:"ul"},"Introduced NutsDeploymentBuilder,NutsIoManager,NutsParseManager,NutsFormatManager,DescriptorFormat"),Object(i.b)("li",{parentName:"ul"},"Introduced NutsSessionTerminal,NutsSystemTerminal"),Object(i.b)("li",{parentName:"ul"},"Added description, alternative (to support multi architecture nuts) descriptor properties"),Object(i.b)("li",{parentName:"ul"},"Removed descriptor/id 'ext' and 'file' parameters. 'packaging' should be more than enough"),Object(i.b)("li",{parentName:"ul"},"Removed Maps from config. Replaced by plain arrays"),Object(i.b)("li",{parentName:"ul"},"Removed workspace.cwd"),Object(i.b)("li",{parentName:"ul"},"Removed Temp File/Folder support"))),Object(i.b)("li",{parentName:"ul"},'Added Archetype "standalone" to help bundling and application with all its dependencies'),Object(i.b)("li",{parentName:"ul"},"Several fixes",Object(i.b)("ul",{parentName:"li"},Object(i.b)("li",{parentName:"ul"},"Fixed Log configuration, introduced --log-inherited to enable inherited log-handlers"),Object(i.b)("li",{parentName:"ul"},"Fixed support for install/uninstall hooks"),Object(i.b)("li",{parentName:"ul"},"Fixed Repository Layout where ref repo folder is created twice"),Object(i.b)("li",{parentName:"ul"},"Fixed Multiple pom download issue"),Object(i.b)("li",{parentName:"ul"},"Fixed Gson parsing issue"),Object(i.b)("li",{parentName:"ul"},"Fixed autocomplete support"),Object(i.b)("li",{parentName:"ul"},"Fixed bad json format recovery"))),Object(i.b)("li",{parentName:"ul"},"nsh ",Object(i.b)("ul",{parentName:"li"},Object(i.b)("li",{parentName:"ul"},"introduced pwd,set unset,alias,unalias,autocomplete commands"),Object(i.b)("li",{parentName:"ul"},"fixed support to autocomplete"))),Object(i.b)("li",{parentName:"ul"},"TODO ",Object(i.b)("ul",{parentName:"li"},Object(i.b)("li",{parentName:"ul"},"Code Comments"),Object(i.b)("li",{parentName:"ul"},"Help files")))),Object(i.b)("h3",{id:"nuts-0520-change-log"},"nuts 0.5.2.0 Change Log"),Object(i.b)("p",null,"WARNING: this version is not deployed to maven-central"),Object(i.b)("ul",null,Object(i.b)("li",{parentName:"ul"},Object(i.b)("inlineCode",{parentName:"li"},"2018/12/28 \tnuts 0.5.2.0")," released ",Object(i.b)("a",Object(a.a)({parentName:"li"},{href:"https://github.com/thevpc/vpc-public-maven/raw/master/net/vpc/app/nuts/nuts/0.5.2/nuts-0.5.2.jar"}),"download nuts-0.5.2.jar")," "),Object(i.b)("li",{parentName:"ul"},"Global refactoring",Object(i.b)("ul",{parentName:"li"},Object(i.b)("li",{parentName:"ul"},"Introduced NutsCommandExecBuilder, NutsDependencyBuilder, NutsDeploymentBuilder, NutsIdBuilder, NutsClassLoaderBuilder"))),Object(i.b)("li",{parentName:"ul"},'Extracted nsh commands as regular nuts package (nadmin, nfind)\nWORKING-ON : Fixing "mvn" start from nuts (handling, exclude, pom import and classifiers from maven)')),Object(i.b)("h3",{id:"nuts-0510-change-log"},"nuts 0.5.1.0 Change Log"),Object(i.b)("p",null,"WARNING: this version is not deployed to maven-central"),Object(i.b)("ul",null,Object(i.b)("li",{parentName:"ul"},Object(i.b)("inlineCode",{parentName:"li"},"2018/12/18 \tnuts 0.5.1.0 released")," ",Object(i.b)("a",Object(a.a)({parentName:"li"},{href:"https://github.com/thevpc/vpc-public-maven/raw/master/net/vpc/app/nuts/nuts/0.5.1/nuts-0.5.1.jar"}),"download nuts-0.5.1.jar")," "),Object(i.b)("li",{parentName:"ul"},"FIXED : Fixed problem with inheritIO from child process (added InputStreamTransparentAdapter and OutputStreamTransparentAdapter interfaces)"),Object(i.b)("li",{parentName:"ul"},"FIXED : Added distinction  between workspace config and runtime boot api/runtime values"),Object(i.b)("li",{parentName:"ul"},"FIXED : Do not read workspace version and dependency config from child process (because it may require distinct version of nuts)"),Object(i.b)("li",{parentName:"ul"},"FIXED : Mkdir,cp, etc... used incorrectly cwd. Fixed."),Object(i.b)("li",{parentName:"ul"},"CHANGED : Optimized pom.xml parse execution time (using DOM instead of SAX)"),Object(i.b)("li",{parentName:"ul"},"CHANGED : moved cache from bootstrap folder to default-workspace/cache")),Object(i.b)("h3",{id:"nuts-0500-change-log"},"nuts 0.5.0.0 Change Log"),Object(i.b)("p",null,"WARNING: this version is not deployed to maven-central"),Object(i.b)("ul",null,Object(i.b)("li",{parentName:"ul"},Object(i.b)("inlineCode",{parentName:"li"},"2018/11/25 \tnuts 0.5.0.0 released")," ",Object(i.b)("a",Object(a.a)({parentName:"li"},{href:"https://github.com/thevpc/vpc-public-maven/raw/master/net/vpc/app/nuts/nuts/0.5.0/nuts-0.5.0.jar"}),"download nuts-0.5.0.jar")," "),Object(i.b)("li",{parentName:"ul"},"Very first published version. older ones were used internally for internal projects only.")))}p.isMDXComponent=!0}}]);