(window.webpackJsonp=window.webpackJsonp||[]).push([[39],{113:function(e,t,a){"use strict";a.d(t,"a",(function(){return O})),a.d(t,"b",(function(){return d}));var n=a(0),l=a.n(n);function r(e,t,a){return t in e?Object.defineProperty(e,t,{value:a,enumerable:!0,configurable:!0,writable:!0}):e[t]=a,e}function b(e,t){var a=Object.keys(e);if(Object.getOwnPropertySymbols){var n=Object.getOwnPropertySymbols(e);t&&(n=n.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),a.push.apply(a,n)}return a}function c(e){for(var t=1;t<arguments.length;t++){var a=null!=arguments[t]?arguments[t]:{};t%2?b(Object(a),!0).forEach((function(t){r(e,t,a[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(a)):b(Object(a)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(a,t))}))}return e}function i(e,t){if(null==e)return{};var a,n,l=function(e,t){if(null==e)return{};var a,n,l={},r=Object.keys(e);for(n=0;n<r.length;n++)a=r[n],t.indexOf(a)>=0||(l[a]=e[a]);return l}(e,t);if(Object.getOwnPropertySymbols){var r=Object.getOwnPropertySymbols(e);for(n=0;n<r.length;n++)a=r[n],t.indexOf(a)>=0||Object.prototype.propertyIsEnumerable.call(e,a)&&(l[a]=e[a])}return l}var u=l.a.createContext({}),p=function(e){var t=l.a.useContext(u),a=t;return e&&(a="function"==typeof e?e(t):c(c({},t),e)),a},O=function(e){var t=p(e.components);return l.a.createElement(u.Provider,{value:t},e.children)},o={inlineCode:"code",wrapper:function(e){var t=e.children;return l.a.createElement(l.a.Fragment,{},t)}},j=l.a.forwardRef((function(e,t){var a=e.components,n=e.mdxType,r=e.originalType,b=e.parentName,u=i(e,["components","mdxType","originalType","parentName"]),O=p(a),j=n,d=O["".concat(b,".").concat(j)]||O[j]||o[j]||r;return a?l.a.createElement(d,c(c({ref:t},u),{},{components:a})):l.a.createElement(d,c({ref:t},u))}));function d(e,t){var a=arguments,n=t&&t.mdxType;if("string"==typeof e||n){var r=a.length,b=new Array(r);b[0]=j;var c={};for(var i in t)hasOwnProperty.call(t,i)&&(c[i]=t[i]);c.originalType=e,c.mdxType="string"==typeof e?e:n,b[1]=c;for(var u=2;u<r;u++)b[u]=a[u];return l.a.createElement.apply(null,b)}return l.a.createElement.apply(null,a)}j.displayName="MDXCreateElement"},93:function(e,t,a){"use strict";a.r(t),a.d(t,"frontMatter",(function(){return b})),a.d(t,"metadata",(function(){return c})),a.d(t,"rightToc",(function(){return i})),a.d(t,"default",(function(){return p}));var n=a(2),l=a(6),r=(a(0),a(113)),b={id:"install-cmd",title:"Install Command",sidebar_label:"Install Command"},c={unversionedId:"cmd/install-cmd",id:"cmd/install-cmd",isDocsHomePage:!1,title:"Install Command",description:"A part from URL and path based executions, an artifact should be installed to be run. Installation can be auto fired when you first execute the artifact (you will be prompted to install the artifact) or manually using the install command. Note that when you run directly a jar file as a path or url, the artifact will not be installed!",source:"@site/docs/cmd/install.md",permalink:"/nuts/docs/cmd/install-cmd",editUrl:"https://github.com/thevpc/nuts/edit/master/website/docs/cmd/install.md",sidebar_label:"Install Command",sidebar:"someSidebar",previous:{title:"Info Command",permalink:"/nuts/docs/cmd/info-cmd"},next:{title:"License Command",permalink:"/nuts/docs/cmd/license-cmd"}},i=[{value:"Purpose",id:"purpose",children:[]}],u={rightToc:i};function p(e){var t=e.components,a=Object(l.a)(e,["components"]);return Object(r.b)("wrapper",Object(n.a)({},u,a,{components:t,mdxType:"MDXLayout"}),Object(r.b)("p",null,"A part from URL and path based executions, an artifact should be installed to be run. Installation can be auto fired when you first execute the artifact (you will be prompted to install the artifact) or manually using the ",Object(r.b)("strong",{parentName:"p"},"install")," command. Note that when you run directly a jar file as a path or url, the artifact will not be installed!\nto install an application just type"),Object(r.b)("pre",null,Object(r.b)("code",Object(n.a)({parentName:"pre"},{}),"nuts install <your-artifact-query-here>\n")),Object(r.b)("p",null,"For example"),Object(r.b)("pre",null,Object(r.b)("code",Object(n.a)({parentName:"pre"},{}),"nuts install net.vpc.app:netbeans-launcher#1.2.2\n")),Object(r.b)("p",null,"you may use any artifact query (see search command section) to install a command."),Object(r.b)("pre",null,Object(r.b)("code",Object(n.a)({parentName:"pre"},{}),"nuts install net.vpc.app:netbeans-*\n")),Object(r.b)("p",null,"if the artifact is already installed, you should use the force flag (--force)"),Object(r.b)("pre",null,Object(r.b)("code",Object(n.a)({parentName:"pre"},{}),"nuts install net.vpc.app:netbeans-launcher#1.2.2\n#this second time we have to force install\nnuts install -- force net.vpc.app:netbeans-launcher#1.2.2\n")),Object(r.b)("p",null,"One exception is when you want to switch between multiple versions installed to set the default one, you can omit the --force flag. Actually, when multiple version of the same artifact are installed all of them are executable directly by specifying the right version. When you specify no version, the default one is selected for you. And to make is simple, the default one is the last one you ran an install command for it."),Object(r.b)("pre",null,Object(r.b)("code",Object(n.a)({parentName:"pre"},{}),"me@linux:~> nuts install net.vpc.app:netbeans-launcher#1.2.2\nme@linux:~> nuts netbeans-launcher\n1.2.2\nme@linux:~> nuts install net.vpc.app:netbeans-launcher#1.2.1\nme@linux:~> nuts netbeans-launcher\n1.2.1\nme@linux:~> nuts install net.vpc.app:netbeans-launcher#1.2.2\n1.2.2\n")),Object(r.b)("p",null,"You can find all installed artifacts using 'nuts search --installed' command"),Object(r.b)("h2",{id:"purpose"},"Purpose"),Object(r.b)("p",null,"The install command is used to install or reinstall packages."),Object(r.b)("ul",null,Object(r.b)("li",{parentName:"ul"},"A+B  : read A main package and B dependencies"),Object(r.b)("li",{parentName:"ul"},"A+B? : ask, if confirmed, read A main package and B dependencies."),Object(r.b)("li",{parentName:"ul"},"require : deploy package as 'required'"),Object(r.b)("li",{parentName:"ul"},"install : deploy package as 'installed'"),Object(r.b)("li",{parentName:"ul"},"nothing : do nothing")),Object(r.b)("p",null,"The available strategies are"),Object(r.b)("ul",null,Object(r.b)("li",{parentName:"ul"},"require   : install the package and all of its dependencies as required class installed package"),Object(r.b)("li",{parentName:"ul"},"install   : install the package and all of its dependencies as first class installed package"),Object(r.b)("li",{parentName:"ul"},"reinstall : re-install or re-required the package and all of its dependencies"),Object(r.b)("li",{parentName:"ul"},"repair    : repair (re-install or re-required) the given dependency")),Object(r.b)("p",null,'"required class installed package" can be removed (uninstalled automatically by nuts when none\nof the depending package is nomore installed.'),Object(r.b)("table",null,Object(r.b)("thead",{parentName:"table"},Object(r.b)("tr",{parentName:"thead"},Object(r.b)("th",Object(n.a)({parentName:"tr"},{align:null}),"Status/Strategy -> Status"),Object(r.b)("th",Object(n.a)({parentName:"tr"},{align:null}),"REQUIRE"),Object(r.b)("th",Object(n.a)({parentName:"tr"},{align:null}),"INSTALL"),Object(r.b)("th",Object(n.a)({parentName:"tr"},{align:null}),"REINSTALL"),Object(r.b)("th",Object(n.a)({parentName:"tr"},{align:null}),"REPAIR"))),Object(r.b)("tbody",{parentName:"table"},Object(r.b)("tr",{parentName:"tbody"},Object(r.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"NOT_INSTALLED"),Object(r.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"REQUIRED"),Object(r.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"INSTALLED"),Object(r.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"INSTALLED?"),Object(r.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"ERROR")),Object(r.b)("tr",{parentName:"tbody"},Object(r.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"INSTALLED"),Object(r.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"INSTALLED REQUIRED"),Object(r.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"INSTALLED?"),Object(r.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"INSTALLED"),Object(r.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"INSTALLED")),Object(r.b)("tr",{parentName:"tbody"},Object(r.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"INSTALLED REQUIRED"),Object(r.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"INSTALLED REQUIRED"),Object(r.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"INSTALLED REQUIRED"),Object(r.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"INSTALLED REQUIRED"),Object(r.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"INSTALLED REQUIRED")),Object(r.b)("tr",{parentName:"tbody"},Object(r.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"REQUIRED"),Object(r.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"REQUIRED"),Object(r.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"INSTALLED REQUIRED"),Object(r.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"REQUIRED"),Object(r.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"REQUIRED")),Object(r.b)("tr",{parentName:"tbody"},Object(r.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"INSTALLED OBSOLETE"),Object(r.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"INSTALLED REQUIRED OBSOLETE"),Object(r.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"INSTALLED"),Object(r.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"INSTALLED"),Object(r.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"INSTALLED")),Object(r.b)("tr",{parentName:"tbody"},Object(r.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"INSTALLED REQUIRED OBSOLETE"),Object(r.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"INSTALLED REQUIRED"),Object(r.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"INSTALLED REQUIRED"),Object(r.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"INSTALLED REQUIRED"),Object(r.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"INSTALLED REQUIRED")),Object(r.b)("tr",{parentName:"tbody"},Object(r.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"REQUIRED OBSOLETE"),Object(r.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"REQUIRED OBSOLETE"),Object(r.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"INSTALLED REQUIRED"),Object(r.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"REQUIRED"),Object(r.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"REQUIRED")))),Object(r.b)("table",null,Object(r.b)("thead",{parentName:"table"},Object(r.b)("tr",{parentName:"thead"},Object(r.b)("th",Object(n.a)({parentName:"tr"},{align:null}),"Status/Strategy -> action"),Object(r.b)("th",Object(n.a)({parentName:"tr"},{align:null}),"REQUIRE"),Object(r.b)("th",Object(n.a)({parentName:"tr"},{align:null}),"INSTALL"),Object(r.b)("th",Object(n.a)({parentName:"tr"},{align:null}),"REINSTALL"),Object(r.b)("th",Object(n.a)({parentName:"tr"},{align:null}),"REPAIR"))),Object(r.b)("tbody",{parentName:"table"},Object(r.b)("tr",{parentName:"tbody"},Object(r.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"NOT_INSTALLED"),Object(r.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"require+require"),Object(r.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"install+require"),Object(r.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"install+require?"),Object(r.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"error")),Object(r.b)("tr",{parentName:"tbody"},Object(r.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"INSTALLED"),Object(r.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"nothing+nothing"),Object(r.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"install+require?"),Object(r.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"install+require"),Object(r.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"install+nothing")),Object(r.b)("tr",{parentName:"tbody"},Object(r.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"INSTALLED REQUIRED"),Object(r.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"nothing+nothing"),Object(r.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"install+require?"),Object(r.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"install+require"),Object(r.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"install+nothing")),Object(r.b)("tr",{parentName:"tbody"},Object(r.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"REQUIRED"),Object(r.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"nothing+nothing"),Object(r.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"install+nothing"),Object(r.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"require+require"),Object(r.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"require+nothing")),Object(r.b)("tr",{parentName:"tbody"},Object(r.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"INSTALLED OBSOLETE"),Object(r.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"install+require"),Object(r.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"install+require"),Object(r.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"install+require"),Object(r.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"install+nothing")),Object(r.b)("tr",{parentName:"tbody"},Object(r.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"INSTALLED REQUIRED OBSOLETE"),Object(r.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"install+require"),Object(r.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"install+require"),Object(r.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"install+require"),Object(r.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"install+nothing")),Object(r.b)("tr",{parentName:"tbody"},Object(r.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"REQUIRED OBSOLETE"),Object(r.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"require+require"),Object(r.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"install+require"),Object(r.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"require+require"),Object(r.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"require+nothing")))))}p.isMDXComponent=!0}}]);