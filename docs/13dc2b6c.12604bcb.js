(window.webpackJsonp=window.webpackJsonp||[]).push([[9],{113:function(e,t,n){"use strict";n.d(t,"a",(function(){return l})),n.d(t,"b",(function(){return b}));var r=n(0),o=n.n(r);function c(e,t,n){return t in e?Object.defineProperty(e,t,{value:n,enumerable:!0,configurable:!0,writable:!0}):e[t]=n,e}function a(e,t){var n=Object.keys(e);if(Object.getOwnPropertySymbols){var r=Object.getOwnPropertySymbols(e);t&&(r=r.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),n.push.apply(n,r)}return n}function i(e){for(var t=1;t<arguments.length;t++){var n=null!=arguments[t]?arguments[t]:{};t%2?a(Object(n),!0).forEach((function(t){c(e,t,n[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(n)):a(Object(n)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(n,t))}))}return e}function s(e,t){if(null==e)return{};var n,r,o=function(e,t){if(null==e)return{};var n,r,o={},c=Object.keys(e);for(r=0;r<c.length;r++)n=c[r],t.indexOf(n)>=0||(o[n]=e[n]);return o}(e,t);if(Object.getOwnPropertySymbols){var c=Object.getOwnPropertySymbols(e);for(r=0;r<c.length;r++)n=c[r],t.indexOf(n)>=0||Object.prototype.propertyIsEnumerable.call(e,n)&&(o[n]=e[n])}return o}var m=o.a.createContext({}),u=function(e){var t=o.a.useContext(m),n=t;return e&&(n="function"==typeof e?e(t):i(i({},t),e)),n},l=function(e){var t=u(e.components);return o.a.createElement(m.Provider,{value:t},e.children)},p={inlineCode:"code",wrapper:function(e){var t=e.children;return o.a.createElement(o.a.Fragment,{},t)}},d=o.a.forwardRef((function(e,t){var n=e.components,r=e.mdxType,c=e.originalType,a=e.parentName,m=s(e,["components","mdxType","originalType","parentName"]),l=u(n),d=r,b=l["".concat(a,".").concat(d)]||l[d]||p[d]||c;return n?o.a.createElement(b,i(i({ref:t},m),{},{components:n})):o.a.createElement(b,i({ref:t},m))}));function b(e,t){var n=arguments,r=t&&t.mdxType;if("string"==typeof e||r){var c=n.length,a=new Array(c);a[0]=d;var i={};for(var s in t)hasOwnProperty.call(t,s)&&(i[s]=t[s]);i.originalType=e,i.mdxType="string"==typeof e?e:r,a[1]=i;for(var m=2;m<c;m++)a[m]=n[m];return o.a.createElement.apply(null,a)}return o.a.createElement.apply(null,n)}d.displayName="MDXCreateElement"},58:function(e,t,n){"use strict";n.r(t),n.d(t,"frontMatter",(function(){return a})),n.d(t,"metadata",(function(){return i})),n.d(t,"rightToc",(function(){return s})),n.d(t,"default",(function(){return u}));var r=n(2),o=n(6),c=(n(0),n(113)),a={id:"welcome-cmd",title:"Welcome Command",sidebar_label:"Welcome Command"},i={unversionedId:"cmd/welcome-cmd",id:"cmd/welcome-cmd",isDocsHomePage:!1,title:"Welcome Command",description:"This command does absolutely nothing but showing this message",source:"@site/docs/cmd/welcome-cmd.md",permalink:"/nuts/docs/cmd/welcome-cmd",editUrl:"https://github.com/thevpc/nuts/edit/master/website/docs/cmd/welcome-cmd.md",sidebar_label:"Welcome Command",sidebar:"someSidebar",previous:{title:"Version Command",permalink:"/nuts/docs/cmd/version-cmd"},next:{title:"Nuts Path",permalink:"/nuts/docs/dev/nutsPath"}},s=[],m={rightToc:s};function u(e){var t=e.components,n=Object(o.a)(e,["components"]);return Object(c.b)("wrapper",Object(r.a)({},m,n,{components:t,mdxType:"MDXLayout"}),Object(c.b)("p",null,"This command does absolutely nothing but showing this message"),Object(c.b)("pre",null,Object(c.b)("code",Object(r.a)({parentName:"pre"},{}),"    _   __      __       \n   / | / /_  __/ /______   Network Updatable Things Services\n  /  |/ / / / / __/ ___/   The Open Source Package Manager for Java (TM)\n / /|  / /_/ / /_(__  )    and other Things ... by vpc\n/_/ |_/\\__,_/\\__/____/     http://github.com/thevpc/nuts\n    version 0.5.7.0\n\n\nSYNOPSIS:\nnuts [<options>]... <command> <args> ...\n\nFor Help, type nuts help\n")))}u.isMDXComponent=!0}}]);