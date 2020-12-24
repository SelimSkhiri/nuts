(window.webpackJsonp=window.webpackJsonp||[]).push([[34],{112:function(e,t,a){"use strict";a.d(t,"a",(function(){return u})),a.d(t,"b",(function(){return m}));var n=a(0),r=a.n(n);function l(e,t,a){return t in e?Object.defineProperty(e,t,{value:a,enumerable:!0,configurable:!0,writable:!0}):e[t]=a,e}function b(e,t){var a=Object.keys(e);if(Object.getOwnPropertySymbols){var n=Object.getOwnPropertySymbols(e);t&&(n=n.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),a.push.apply(a,n)}return a}function c(e){for(var t=1;t<arguments.length;t++){var a=null!=arguments[t]?arguments[t]:{};t%2?b(Object(a),!0).forEach((function(t){l(e,t,a[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(a)):b(Object(a)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(a,t))}))}return e}function i(e,t){if(null==e)return{};var a,n,r=function(e,t){if(null==e)return{};var a,n,r={},l=Object.keys(e);for(n=0;n<l.length;n++)a=l[n],t.indexOf(a)>=0||(r[a]=e[a]);return r}(e,t);if(Object.getOwnPropertySymbols){var l=Object.getOwnPropertySymbols(e);for(n=0;n<l.length;n++)a=l[n],t.indexOf(a)>=0||Object.prototype.propertyIsEnumerable.call(e,a)&&(r[a]=e[a])}return r}var O=r.a.createContext({}),j=function(e){var t=r.a.useContext(O),a=t;return e&&(a="function"==typeof e?e(t):c(c({},t),e)),a},u=function(e){var t=j(e.components);return r.a.createElement(O.Provider,{value:t},e.children)},p={inlineCode:"code",wrapper:function(e){var t=e.children;return r.a.createElement(r.a.Fragment,{},t)}},d=r.a.forwardRef((function(e,t){var a=e.components,n=e.mdxType,l=e.originalType,b=e.parentName,O=i(e,["components","mdxType","originalType","parentName"]),u=j(a),d=n,m=u["".concat(b,".").concat(d)]||u[d]||p[d]||l;return a?r.a.createElement(m,c(c({ref:t},O),{},{components:a})):r.a.createElement(m,c({ref:t},O))}));function m(e,t){var a=arguments,n=t&&t.mdxType;if("string"==typeof e||n){var l=a.length,b=new Array(l);b[0]=d;var c={};for(var i in t)hasOwnProperty.call(t,i)&&(c[i]=t[i]);c.originalType=e,c.mdxType="string"==typeof e?e:n,b[1]=c;for(var O=2;O<l;O++)b[O]=a[O];return r.a.createElement.apply(null,b)}return r.a.createElement.apply(null,a)}d.displayName="MDXCreateElement"},88:function(e,t,a){"use strict";a.r(t),a.d(t,"frontMatter",(function(){return b})),a.d(t,"metadata",(function(){return c})),a.d(t,"rightToc",(function(){return i})),a.d(t,"default",(function(){return j}));var n=a(2),r=a(6),l=(a(0),a(112)),b={id:"install-cmd",title:"Install Command",sidebar_label:"Install Command"},c={unversionedId:"cmd/install-cmd",id:"cmd/install-cmd",isDocsHomePage:!1,title:"Install Command",description:"`",source:"@site/docs/cmd/install.md",permalink:"/nuts/docs/cmd/install-cmd",editUrl:"https://github.com/facebook/docusaurus/edit/master/website/docs/cmd/install.md",sidebar_label:"Install Command",sidebar:"someSidebar",previous:{title:"Style Guide",permalink:"/nuts/docs/advanced/doc1"},next:{title:"Command Line Arguments",permalink:"/nuts/docs/concepts/commandline"}},i=[{value:"Purpose",id:"purpose",children:[]}],O={rightToc:i};function j(e){var t=e.components,a=Object(r.a)(e,["components"]);return Object(l.b)("wrapper",Object(n.a)({},O,a,{components:t,mdxType:"MDXLayout"}),Object(l.b)("pre",null,Object(l.b)("code",Object(n.a)({parentName:"pre"},{}),"     __        __    \n  /\\ \\ \\ _  __/ /______\n /  \\/ / / / / __/ ___/\n/ /\\  / /_/ / /_(__  )\n\\_\\ \\/\\__,_/\\__/____/    version v0.8.1\n")),Object(l.b)("h2",{id:"purpose"},"Purpose"),Object(l.b)("p",null,"The install command is used to install or reinstall packages."),Object(l.b)("ul",null,Object(l.b)("li",{parentName:"ul"},"A+B  : read A main package and B dependencies"),Object(l.b)("li",{parentName:"ul"},"A+B? : ask, if confirmed, read A main package and B dependencies."),Object(l.b)("li",{parentName:"ul"},"require : deploy package as 'required'"),Object(l.b)("li",{parentName:"ul"},"install : deploy package as 'installed'"),Object(l.b)("li",{parentName:"ul"},"nothing : do nothing")),Object(l.b)("p",null,"The available strategies are"),Object(l.b)("ul",null,Object(l.b)("li",{parentName:"ul"},"require   : install the package and all of its dependencies as required class installed package"),Object(l.b)("li",{parentName:"ul"},"install   : install the package and all of its dependencies as first class installed package"),Object(l.b)("li",{parentName:"ul"},"reinstall : re-install or re-required the package and all of its dependencies"),Object(l.b)("li",{parentName:"ul"},"repair    : repair (re-install or re-required) the given dependency")),Object(l.b)("p",null,'"required class installed package" can be removed (uninstalled automatically by nuts when none\nof the depending package is nomore installed.'),Object(l.b)("table",null,Object(l.b)("thead",{parentName:"table"},Object(l.b)("tr",{parentName:"thead"},Object(l.b)("th",Object(n.a)({parentName:"tr"},{align:null}),"Status/Strategy -> Status"),Object(l.b)("th",Object(n.a)({parentName:"tr"},{align:null}),"REQUIRE"),Object(l.b)("th",Object(n.a)({parentName:"tr"},{align:null}),"INSTALL"),Object(l.b)("th",Object(n.a)({parentName:"tr"},{align:null}),"REINSTALL"),Object(l.b)("th",Object(n.a)({parentName:"tr"},{align:null}),"REPAIR"))),Object(l.b)("tbody",{parentName:"table"},Object(l.b)("tr",{parentName:"tbody"},Object(l.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"NOT_INSTALLED"),Object(l.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"REQUIRED"),Object(l.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"INSTALLED"),Object(l.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"INSTALLED?"),Object(l.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"ERROR")),Object(l.b)("tr",{parentName:"tbody"},Object(l.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"INSTALLED"),Object(l.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"INSTALLED REQUIRED"),Object(l.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"INSTALLED?"),Object(l.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"INSTALLED"),Object(l.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"INSTALLED")),Object(l.b)("tr",{parentName:"tbody"},Object(l.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"INSTALLED REQUIRED"),Object(l.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"INSTALLED REQUIRED"),Object(l.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"INSTALLED REQUIRED"),Object(l.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"INSTALLED REQUIRED"),Object(l.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"INSTALLED REQUIRED")),Object(l.b)("tr",{parentName:"tbody"},Object(l.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"REQUIRED"),Object(l.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"REQUIRED"),Object(l.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"INSTALLED REQUIRED"),Object(l.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"REQUIRED"),Object(l.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"REQUIRED")),Object(l.b)("tr",{parentName:"tbody"},Object(l.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"INSTALLED OBSOLETE"),Object(l.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"INSTALLED REQUIRED OBSOLETE"),Object(l.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"INSTALLED"),Object(l.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"INSTALLED"),Object(l.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"INSTALLED")),Object(l.b)("tr",{parentName:"tbody"},Object(l.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"INSTALLED REQUIRED OBSOLETE"),Object(l.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"INSTALLED REQUIRED"),Object(l.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"INSTALLED REQUIRED"),Object(l.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"INSTALLED REQUIRED"),Object(l.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"INSTALLED REQUIRED")),Object(l.b)("tr",{parentName:"tbody"},Object(l.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"REQUIRED OBSOLETE"),Object(l.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"REQUIRED OBSOLETE"),Object(l.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"INSTALLED REQUIRED"),Object(l.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"REQUIRED"),Object(l.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"REQUIRED")))),Object(l.b)("table",null,Object(l.b)("thead",{parentName:"table"},Object(l.b)("tr",{parentName:"thead"},Object(l.b)("th",Object(n.a)({parentName:"tr"},{align:null}),"Status/Strategy -> action"),Object(l.b)("th",Object(n.a)({parentName:"tr"},{align:null}),"REQUIRE"),Object(l.b)("th",Object(n.a)({parentName:"tr"},{align:null}),"INSTALL"),Object(l.b)("th",Object(n.a)({parentName:"tr"},{align:null}),"REINSTALL"),Object(l.b)("th",Object(n.a)({parentName:"tr"},{align:null}),"REPAIR"))),Object(l.b)("tbody",{parentName:"table"},Object(l.b)("tr",{parentName:"tbody"},Object(l.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"NOT_INSTALLED"),Object(l.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"require+require"),Object(l.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"install+require"),Object(l.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"install+require?"),Object(l.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"error")),Object(l.b)("tr",{parentName:"tbody"},Object(l.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"INSTALLED"),Object(l.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"nothing+nothing"),Object(l.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"install+require?"),Object(l.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"install+require"),Object(l.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"install+nothing")),Object(l.b)("tr",{parentName:"tbody"},Object(l.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"INSTALLED REQUIRED"),Object(l.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"nothing+nothing"),Object(l.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"install+require?"),Object(l.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"install+require"),Object(l.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"install+nothing")),Object(l.b)("tr",{parentName:"tbody"},Object(l.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"REQUIRED"),Object(l.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"nothing+nothing"),Object(l.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"install+nothing"),Object(l.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"require+require"),Object(l.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"require+nothing")),Object(l.b)("tr",{parentName:"tbody"},Object(l.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"INSTALLED OBSOLETE"),Object(l.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"install+require"),Object(l.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"install+require"),Object(l.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"install+require"),Object(l.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"install+nothing")),Object(l.b)("tr",{parentName:"tbody"},Object(l.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"INSTALLED REQUIRED OBSOLETE"),Object(l.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"install+require"),Object(l.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"install+require"),Object(l.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"install+require"),Object(l.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"install+nothing")),Object(l.b)("tr",{parentName:"tbody"},Object(l.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"REQUIRED OBSOLETE"),Object(l.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"require+require"),Object(l.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"install+require"),Object(l.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"require+require"),Object(l.b)("td",Object(n.a)({parentName:"tr"},{align:null}),"require+nothing")))))}j.isMDXComponent=!0}}]);