(window.webpackJsonp=window.webpackJsonp||[]).push([[44],{112:function(e,t,n){"use strict";n.d(t,"a",(function(){return p})),n.d(t,"b",(function(){return m}));var r=n(0),o=n.n(r);function a(e,t,n){return t in e?Object.defineProperty(e,t,{value:n,enumerable:!0,configurable:!0,writable:!0}):e[t]=n,e}function i(e,t){var n=Object.keys(e);if(Object.getOwnPropertySymbols){var r=Object.getOwnPropertySymbols(e);t&&(r=r.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),n.push.apply(n,r)}return n}function c(e){for(var t=1;t<arguments.length;t++){var n=null!=arguments[t]?arguments[t]:{};t%2?i(Object(n),!0).forEach((function(t){a(e,t,n[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(n)):i(Object(n)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(n,t))}))}return e}function l(e,t){if(null==e)return{};var n,r,o=function(e,t){if(null==e)return{};var n,r,o={},a=Object.keys(e);for(r=0;r<a.length;r++)n=a[r],t.indexOf(n)>=0||(o[n]=e[n]);return o}(e,t);if(Object.getOwnPropertySymbols){var a=Object.getOwnPropertySymbols(e);for(r=0;r<a.length;r++)n=a[r],t.indexOf(n)>=0||Object.prototype.propertyIsEnumerable.call(e,n)&&(o[n]=e[n])}return o}var s=o.a.createContext({}),u=function(e){var t=o.a.useContext(s),n=t;return e&&(n="function"==typeof e?e(t):c(c({},t),e)),n},p=function(e){var t=u(e.components);return o.a.createElement(s.Provider,{value:t},e.children)},d={inlineCode:"code",wrapper:function(e){var t=e.children;return o.a.createElement(o.a.Fragment,{},t)}},b=o.a.forwardRef((function(e,t){var n=e.components,r=e.mdxType,a=e.originalType,i=e.parentName,s=l(e,["components","mdxType","originalType","parentName"]),p=u(n),b=r,m=p["".concat(i,".").concat(b)]||p[b]||d[b]||a;return n?o.a.createElement(m,c(c({ref:t},s),{},{components:n})):o.a.createElement(m,c({ref:t},s))}));function m(e,t){var n=arguments,r=t&&t.mdxType;if("string"==typeof e||r){var a=n.length,i=new Array(a);i[0]=b;var c={};for(var l in t)hasOwnProperty.call(t,l)&&(c[l]=t[l]);c.originalType=e,c.mdxType="string"==typeof e?e:r,i[1]=c;for(var s=2;s<a;s++)i[s]=n[s];return o.a.createElement.apply(null,i)}return o.a.createElement.apply(null,n)}b.displayName="MDXCreateElement"},146:function(e,t,n){"use strict";n.r(t),t.default=n.p+"assets/images/text-coloring-format-182308b5ebe17e87d136191ee882e075.png"},97:function(e,t,n){"use strict";n.r(t),n.d(t,"frontMatter",(function(){return i})),n.d(t,"metadata",(function(){return c})),n.d(t,"rightToc",(function(){return l})),n.d(t,"default",(function(){return u}));var r=n(2),o=n(6),a=(n(0),n(112)),i={id:"doc1",title:"Style Guide",sidebar_label:"Style Guide"},c={unversionedId:"advanced/doc1",id:"advanced/doc1",isDocsHomePage:!1,title:"Style Guide",description:"`",source:"@site/docs/advanced/text-coloring-format.md",permalink:"/nuts/docs/advanced/doc1",editUrl:"https://github.com/facebook/docusaurus/edit/master/website/docs/advanced/text-coloring-format.md",sidebar_label:"Style Guide",sidebar:"someSidebar",previous:{title:"Portable Workspaces",permalink:"/nuts/docs/advanced/portableWorkspaces"},next:{title:"Install Command",permalink:"/nuts/docs/cmd/install-cmd"}},l=[],s={rightToc:l};function u(e){var t=e.components,i=Object(o.a)(e,["components"]);return Object(a.b)("wrapper",Object(r.a)({},s,i,{components:t,mdxType:"MDXLayout"}),Object(a.b)("pre",null,Object(a.b)("code",Object(r.a)({parentName:"pre"},{}),"     __        __    \n  /\\ \\ \\ _  __/ /______\n /  \\/ / / / / __/ ___/\n/ /\\  / /_/ / /_(__  )\n\\_\\ \\/\\__,_/\\__/____/    version v0.8.1\n")),Object(a.b)("h1",{id:"nuts-text-coloring-format"},"Nuts Text Coloring Format"),Object(a.b)("p",null,Object(a.b)("strong",{parentName:"p"},Object(a.b)("inlineCode",{parentName:"strong"},"nuts")),' comes up with a simple coloring syntax that helps writing better looking portable command line programs.\nstandard output is automatically configured to accept the "Nuts Text Coloring Format" (NTCF) syntax.\nThough it remains possible to disable this ability using the --no-color standard option (or programmatically,\nsee ',Object(a.b)("strong",{parentName:"p"},Object(a.b)("inlineCode",{parentName:"strong"},"nuts"))," API documentation). NTCF will be translated to the underlying terminal implementation using ANSI\nescape code on linux/windows terminals if available."),Object(a.b)("p",null,"Here after a showcase of available NTCF syntax."),Object(a.b)("p",null,Object(a.b)("img",{alt:"text-coloring-format",src:n(146).default})))}u.isMDXComponent=!0}}]);