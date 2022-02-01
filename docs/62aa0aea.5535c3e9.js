(window.webpackJsonp=window.webpackJsonp||[]).push([[25],{121:function(e,t,a){"use strict";a.d(t,"a",(function(){return u}));var n=a(0),r=a.n(n);function o(e,t,a){return t in e?Object.defineProperty(e,t,{value:a,enumerable:!0,configurable:!0,writable:!0}):e[t]=a,e}function i(e,t){var a=Object.keys(e);if(Object.getOwnPropertySymbols){var n=Object.getOwnPropertySymbols(e);t&&(n=n.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),a.push.apply(a,n)}return a}function s(e){for(var t=1;t<arguments.length;t++){var a=null!=arguments[t]?arguments[t]:{};t%2?i(Object(a),!0).forEach((function(t){o(e,t,a[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(a)):i(Object(a)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(a,t))}))}return e}function c(e,t){if(null==e)return{};var a,n,r=function(e,t){if(null==e)return{};var a,n,r={},o=Object.keys(e);for(n=0;n<o.length;n++)a=o[n],t.indexOf(a)>=0||(r[a]=e[a]);return r}(e,t);if(Object.getOwnPropertySymbols){var o=Object.getOwnPropertySymbols(e);for(n=0;n<o.length;n++)a=o[n],t.indexOf(a)>=0||Object.prototype.propertyIsEnumerable.call(e,a)&&(r[a]=e[a])}return r}var l=r.a.createContext({}),p=function(e){var t=r.a.useContext(l),a=t;return e&&(a="function"==typeof e?e(t):s(s({},t),e)),a},d={inlineCode:"code",wrapper:function(e){var t=e.children;return r.a.createElement(r.a.Fragment,{},t)}},m=r.a.forwardRef((function(e,t){var a=e.components,n=e.mdxType,o=e.originalType,i=e.parentName,l=c(e,["components","mdxType","originalType","parentName"]),m=p(a),u=n,b=m["".concat(i,".").concat(u)]||m[u]||d[u]||o;return a?r.a.createElement(b,s(s({ref:t},l),{},{components:a})):r.a.createElement(b,s({ref:t},l))}));function u(e,t){var a=arguments,n=t&&t.mdxType;if("string"==typeof e||n){var o=a.length,i=new Array(o);i[0]=m;var s={};for(var c in t)hasOwnProperty.call(t,c)&&(s[c]=t[c]);s.originalType=e,s.mdxType="string"==typeof e?e:n,i[1]=s;for(var l=2;l<o;l++)i[l]=a[l];return r.a.createElement.apply(null,i)}return r.a.createElement.apply(null,a)}m.displayName="MDXCreateElement"},81:function(e,t,a){"use strict";a.r(t),a.d(t,"frontMatter",(function(){return i})),a.d(t,"metadata",(function(){return s})),a.d(t,"rightToc",(function(){return c})),a.d(t,"default",(function(){return p}));var n=a(2),r=a(6),o=(a(0),a(121)),i={id:"introduction",title:"Introduction",sidebar_label:"Introduction",order:1},s={unversionedId:"intro/introduction",id:"intro/introduction",isDocsHomePage:!0,title:"Introduction",description:"`nuts` stands for Network Updatable Things Services tool and is a portable package manager for java (mainly) that handles remote artifacts, installs these artifacts to the current machine and executes such artifacts on need.",source:"@site/docs/intro/introduction.md",permalink:"/nuts/docs/",editUrl:"https://github.com/thevpc/nuts/edit/master/website/docs/intro/introduction.md",sidebar_label:"Introduction",sidebar:"someSidebar",next:{title:"Nuts and Maven",permalink:"/nuts/docs/intro/nutsAndMaven"}},c=[],l={rightToc:c};function p(e){var t=e.components,a=Object(r.a)(e,["components"]);return Object(o.a)("wrapper",Object(n.a)({},l,a,{components:t,mdxType:"MDXLayout"}),Object(o.a)("p",null,Object(o.a)("strong",{parentName:"p"},Object(o.a)("inlineCode",{parentName:"strong"},"nuts"))," stands for ",Object(o.a)("strong",{parentName:"p"},"Network Updatable Things Services")," tool and is a portable package manager for java (mainly) that handles remote artifacts, installs these artifacts to the current machine and executes such artifacts on need.\n",Object(o.a)("strong",{parentName:"p"},Object(o.a)("inlineCode",{parentName:"strong"},"nuts"))," solves the ",Object(o.a)("strong",{parentName:"p"},"fatjar")," problem delegating the dependency resolution to the time when the application is to be executed and\nsimplifies the packaging process while being transparent to the build process. Actually, nuts uses ",Object(o.a)("strong",{parentName:"p"},"maven")," ",Object(o.a)("strong",{parentName:"p"},"pom")," descriptors to resolve\ndependencies when the artifact is installed on the target machine, and it can use also other types of descriptors for other types of packages."),Object(o.a)("p",null,Object(o.a)("strong",{parentName:"p"},Object(o.a)("inlineCode",{parentName:"strong"},"nuts"))," artifacts are  stored  into repositories. A  ",Object(o.a)("strong",{parentName:"p"},"repository"),"  may be local for  storing local artifacts or remote for accessing remote artifacts (good examples  are  remote maven  repositories). It may also be a proxy repository so that remote artifacts are fetched and cached locally to save network resources."),Object(o.a)("p",null,"One manages a set of repositories called a  workspace (like ",Object(o.a)("strong",{parentName:"p"},"virtualenv")," in ",Object(o.a)("strong",{parentName:"p"},Object(o.a)("inlineCode",{parentName:"strong"},"pip")),"). Managed ",Object(o.a)("strong",{parentName:"p"},Object(o.a)("inlineCode",{parentName:"strong"},"nuts")),"  (artifacts)  have descriptors that depicts dependencies between them. This dependency is seamlessly handled by  ",Object(o.a)("strong",{parentName:"p"},Object(o.a)("inlineCode",{parentName:"strong"},"nuts")),"  (tool) to resolve and download on-need dependencies over the wire."),Object(o.a)("p",null,Object(o.a)("strong",{parentName:"p"},Object(o.a)("inlineCode",{parentName:"strong"},"nuts"))," is a swiss army knife tool as it acts like (and supports) ",Object(o.a)("strong",{parentName:"p"},"maven")," build tool to have an abstract view of the artifacts\ndependency and like  ",Object(o.a)("strong",{parentName:"p"},"npm")," and ",Object(o.a)("strong",{parentName:"p"},"pip")," language package managers to  install and uninstall artifacts allowing multiple versions of the very\nsame artifact to  be installed. ",Object(o.a)("strong",{parentName:"p"},Object(o.a)("inlineCode",{parentName:"strong"},"nuts"))," is not exclusive for Java/Scala/Kotlin and other Java Platform Languages, by design it supports\nmultiple artifact formats other than jars and wars and is able to select the appropriate artifacts and dependencies according to the current OS, architecture and even Desktop Environment."),Object(o.a)("p",null,Object(o.a)("strong",{parentName:"p"},Object(o.a)("inlineCode",{parentName:"strong"},"nuts"))," common verbs are:"),Object(o.a)("ul",null,Object(o.a)("li",{parentName:"ul"},Object(o.a)("inlineCode",{parentName:"li"},"exec"),"               : execute an artifact or a command"),Object(o.a)("li",{parentName:"ul"},Object(o.a)("inlineCode",{parentName:"li"},"which"),"              : detect the proper artifact or system command to execute"),Object(o.a)("li",{parentName:"ul"},Object(o.a)("inlineCode",{parentName:"li"},"install"),", ",Object(o.a)("inlineCode",{parentName:"li"},"uninstall")," : install/uninstall an artifact (using its fetched/deployed installer)"),Object(o.a)("li",{parentName:"ul"},Object(o.a)("inlineCode",{parentName:"li"},"update"),",",Object(o.a)("inlineCode",{parentName:"li"},"check-updates"),"  : search for updates"),Object(o.a)("li",{parentName:"ul"},Object(o.a)("inlineCode",{parentName:"li"},"deploy"),", ",Object(o.a)("inlineCode",{parentName:"li"},"undeploy"),"   : manage artifacts (artifact installers) on the local repositories"),Object(o.a)("li",{parentName:"ul"},Object(o.a)("inlineCode",{parentName:"li"},"fetch"),", ",Object(o.a)("inlineCode",{parentName:"li"},"push"),"        : download from, upload to remote repositories"),Object(o.a)("li",{parentName:"ul"},Object(o.a)("inlineCode",{parentName:"li"},"search"),"             : search for existing/installable artifacts"),Object(o.a)("li",{parentName:"ul"},Object(o.a)("inlineCode",{parentName:"li"},"welcome"),"            : a command that does nothing but bootstrapping ",Object(o.a)("strong",{parentName:"li"},Object(o.a)("inlineCode",{parentName:"strong"},"nuts"))," and showing a welcome message.")))}p.isMDXComponent=!0}}]);