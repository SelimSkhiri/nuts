(window.webpackJsonp=window.webpackJsonp||[]).push([[36],{121:function(e,t,n){"use strict";n.d(t,"a",(function(){return d}));var r=n(0),o=n.n(r);function a(e,t,n){return t in e?Object.defineProperty(e,t,{value:n,enumerable:!0,configurable:!0,writable:!0}):e[t]=n,e}function i(e,t){var n=Object.keys(e);if(Object.getOwnPropertySymbols){var r=Object.getOwnPropertySymbols(e);t&&(r=r.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),n.push.apply(n,r)}return n}function c(e){for(var t=1;t<arguments.length;t++){var n=null!=arguments[t]?arguments[t]:{};t%2?i(Object(n),!0).forEach((function(t){a(e,t,n[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(n)):i(Object(n)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(n,t))}))}return e}function s(e,t){if(null==e)return{};var n,r,o=function(e,t){if(null==e)return{};var n,r,o={},a=Object.keys(e);for(r=0;r<a.length;r++)n=a[r],t.indexOf(n)>=0||(o[n]=e[n]);return o}(e,t);if(Object.getOwnPropertySymbols){var a=Object.getOwnPropertySymbols(e);for(r=0;r<a.length;r++)n=a[r],t.indexOf(n)>=0||Object.prototype.propertyIsEnumerable.call(e,n)&&(o[n]=e[n])}return o}var p=o.a.createContext({}),m=function(e){var t=o.a.useContext(p),n=t;return e&&(n="function"==typeof e?e(t):c(c({},t),e)),n},l={inlineCode:"code",wrapper:function(e){var t=e.children;return o.a.createElement(o.a.Fragment,{},t)}},u=o.a.forwardRef((function(e,t){var n=e.components,r=e.mdxType,a=e.originalType,i=e.parentName,p=s(e,["components","mdxType","originalType","parentName"]),u=m(n),d=r,f=u["".concat(i,".").concat(d)]||u[d]||l[d]||a;return n?o.a.createElement(f,c(c({ref:t},p),{},{components:n})):o.a.createElement(f,c({ref:t},p))}));function d(e,t){var n=arguments,r=t&&t.mdxType;if("string"==typeof e||r){var a=n.length,i=new Array(a);i[0]=u;var c={};for(var s in t)hasOwnProperty.call(t,s)&&(c[s]=t[s]);c.originalType=e,c.mdxType="string"==typeof e?e:r,i[1]=c;for(var p=2;p<a;p++)i[p]=n[p];return o.a.createElement.apply(null,i)}return o.a.createElement.apply(null,n)}u.displayName="MDXCreateElement"},92:function(e,t,n){"use strict";n.r(t),n.d(t,"frontMatter",(function(){return i})),n.d(t,"metadata",(function(){return c})),n.d(t,"rightToc",(function(){return s})),n.d(t,"default",(function(){return m}));var r=n(2),o=n(6),a=(n(0),n(121)),i={id:"version-cmd",title:"Version Command",sidebar_label:"Version Command"},c={unversionedId:"cmd/version-cmd",id:"cmd/version-cmd",isDocsHomePage:!1,title:"Version Command",description:"This command will show nuts version. It is helpful to note that nuts has a couple of components : api and impl.",source:"@site/docs/cmd/version-cmd.md",permalink:"/nuts/docs/cmd/version-cmd",editUrl:"https://github.com/thevpc/nuts/edit/master/website/docs/cmd/version-cmd.md",sidebar_label:"Version Command",sidebar:"someSidebar",previous:{title:"Search Command",permalink:"/nuts/docs/cmd/search-cmds"},next:{title:"Welcome Command",permalink:"/nuts/docs/cmd/welcome-cmd"}},s=[],p={rightToc:s};function m(e){var t=e.components,n=Object(o.a)(e,["components"]);return Object(a.a)("wrapper",Object(r.a)({},p,n,{components:t,mdxType:"MDXLayout"}),Object(a.a)("p",null,"This command will show ",Object(a.a)("strong",{parentName:"p"},"nuts")," version. It is helpful to note that ",Object(a.a)("strong",{parentName:"p"},"nuts")," has a couple of components : api and impl.\napi is the ",Object(a.a)("strong",{parentName:"p"},"nuts")," bootstrap jar (actually nuts-*.jar, ~500Ko of size) that contains only the minimum code to use nuts and to download the full implementation (3Mo of size) : impl component. Usually, the implementation version starts with the api version but this should be no rule."),Object(a.a)("pre",null,Object(a.a)("code",Object(r.a)({parentName:"pre"},{}),"me@linux:~> nuts version\n0.8.3/0.8.3.1\n")),Object(a.a)("p",null,"Here the ",Object(a.a)("strong",{parentName:"p"},"version")," command show api version (0.8.3) and the impl version (0.8.3.1)"))}m.isMDXComponent=!0}}]);