(window.webpackJsonp=window.webpackJsonp||[]).push([[38],{103:function(e,t,n){"use strict";n.d(t,"a",(function(){return p})),n.d(t,"b",(function(){return f}));var r=n(0),a=n.n(r);function o(e,t,n){return t in e?Object.defineProperty(e,t,{value:n,enumerable:!0,configurable:!0,writable:!0}):e[t]=n,e}function c(e,t){var n=Object.keys(e);if(Object.getOwnPropertySymbols){var r=Object.getOwnPropertySymbols(e);t&&(r=r.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),n.push.apply(n,r)}return n}function i(e){for(var t=1;t<arguments.length;t++){var n=null!=arguments[t]?arguments[t]:{};t%2?c(Object(n),!0).forEach((function(t){o(e,t,n[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(n)):c(Object(n)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(n,t))}))}return e}function l(e,t){if(null==e)return{};var n,r,a=function(e,t){if(null==e)return{};var n,r,a={},o=Object.keys(e);for(r=0;r<o.length;r++)n=o[r],t.indexOf(n)>=0||(a[n]=e[n]);return a}(e,t);if(Object.getOwnPropertySymbols){var o=Object.getOwnPropertySymbols(e);for(r=0;r<o.length;r++)n=o[r],t.indexOf(n)>=0||Object.prototype.propertyIsEnumerable.call(e,n)&&(a[n]=e[n])}return a}var s=a.a.createContext({}),u=function(e){var t=a.a.useContext(s),n=t;return e&&(n="function"==typeof e?e(t):i(i({},t),e)),n},p=function(e){var t=u(e.components);return a.a.createElement(s.Provider,{value:t},e.children)},d={inlineCode:"code",wrapper:function(e){var t=e.children;return a.a.createElement(a.a.Fragment,{},t)}},b=a.a.forwardRef((function(e,t){var n=e.components,r=e.mdxType,o=e.originalType,c=e.parentName,s=l(e,["components","mdxType","originalType","parentName"]),p=u(n),b=r,f=p["".concat(c,".").concat(b)]||p[b]||d[b]||o;return n?a.a.createElement(f,i(i({ref:t},s),{},{components:n})):a.a.createElement(f,i({ref:t},s))}));function f(e,t){var n=arguments,r=t&&t.mdxType;if("string"==typeof e||r){var o=n.length,c=new Array(o);c[0]=b;var i={};for(var l in t)hasOwnProperty.call(t,l)&&(i[l]=t[l]);i.originalType=e,i.mdxType="string"==typeof e?e:r,c[1]=i;for(var s=2;s<o;s++)c[s]=n[s];return a.a.createElement.apply(null,c)}return a.a.createElement.apply(null,n)}b.displayName="MDXCreateElement"},137:function(e,t,n){"use strict";n.r(t),t.default=n.p+"assets/images/text-coloring-format-01-5b1dd5fe85c9caffa7ee5c1cfa13b4b4.png"},138:function(e,t,n){"use strict";n.r(t),t.default=n.p+"assets/images/text-coloring-format-02-5a95010416ccb994ea530d6d5e7895a4.png"},139:function(e,t,n){"use strict";n.r(t),t.default=n.p+"assets/images/text-coloring-format-03-070e5ac1627273bb68eb48e817d1a0de.png"},140:function(e,t,n){"use strict";n.r(t),t.default=n.p+"assets/images/text-coloring-format-04-adafcacd81372630f95721c0690f2b47.png"},91:function(e,t,n){"use strict";n.r(t),n.d(t,"frontMatter",(function(){return c})),n.d(t,"metadata",(function(){return i})),n.d(t,"rightToc",(function(){return l})),n.d(t,"default",(function(){return u}));var r=n(2),a=n(6),o=(n(0),n(103)),c={id:"doc1",title:"Style Guide",sidebar_label:"Style Guide"},i={unversionedId:"advanced/doc1",id:"advanced/doc1",isDocsHomePage:!1,title:"Style Guide",description:"`",source:"@site/docs/advanced/text-coloring-format.md",permalink:"/nuts/docs/advanced/doc1",editUrl:"https://github.com/facebook/docusaurus/edit/master/website/docs/advanced/text-coloring-format.md",sidebar_label:"Style Guide",sidebar:"someSidebar",previous:{title:"Portable Workspaces",permalink:"/nuts/docs/advanced/portableWorkspaces"},next:{title:"Install Command",permalink:"/nuts/docs/cmd/install-cmd"}},l=[],s={rightToc:l};function u(e){var t=e.components,c=Object(a.a)(e,["components"]);return Object(o.b)("wrapper",Object(r.a)({},s,c,{components:t,mdxType:"MDXLayout"}),Object(o.b)("pre",null,Object(o.b)("code",Object(r.a)({parentName:"pre"},{}),"     __        __    \n  /\\ \\ \\ _  __/ /______\n /  \\/ / / / / __/ ___/\n/ /\\  / /_/ / /_(__  )\n\\_\\ \\/\\__,_/\\__/____/    version v0.8.1\n")),Object(o.b)("h1",{id:"nuts-text-format"},"Nuts Text Format"),Object(o.b)("p",null,Object(o.b)("strong",{parentName:"p"},Object(o.b)("inlineCode",{parentName:"strong"},"nuts")),' comes up with a simple coloring syntax that helps writing better looking portable command line programs.\nstandard output is automatically configured to accept the "Nuts Text Format" (NTF) syntax.\nThough it remains possible to disable this ability using the --no-color standard option (or programmatically,\nsee ',Object(o.b)("strong",{parentName:"p"},Object(o.b)("inlineCode",{parentName:"strong"},"nuts"))," API documentation). NTF will be translated to the underlying terminal implementation using ANSI\nescape code on linux/windows terminals if available."),Object(o.b)("p",null,"Here after a showcase of available NTF syntax."),Object(o.b)("p",null,Object(o.b)("img",{alt:"text-coloring-format",src:n(137).default})),Object(o.b)("p",null,Object(o.b)("img",{alt:"text-coloring-format",src:n(138).default})),Object(o.b)("p",null,Object(o.b)("img",{alt:"text-coloring-format",src:n(139).default})),Object(o.b)("p",null,Object(o.b)("img",{alt:"text-coloring-format",src:n(140).default})))}u.isMDXComponent=!0}}]);