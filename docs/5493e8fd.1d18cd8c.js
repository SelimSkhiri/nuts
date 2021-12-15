(window.webpackJsonp=window.webpackJsonp||[]).push([[24],{115:function(e,t,r){"use strict";r.d(t,"a",(function(){return m}));var n=r(0),a=r.n(n);function o(e,t,r){return t in e?Object.defineProperty(e,t,{value:r,enumerable:!0,configurable:!0,writable:!0}):e[t]=r,e}function i(e,t){var r=Object.keys(e);if(Object.getOwnPropertySymbols){var n=Object.getOwnPropertySymbols(e);t&&(n=n.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),r.push.apply(r,n)}return r}function c(e){for(var t=1;t<arguments.length;t++){var r=null!=arguments[t]?arguments[t]:{};t%2?i(Object(r),!0).forEach((function(t){o(e,t,r[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(r)):i(Object(r)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(r,t))}))}return e}function s(e,t){if(null==e)return{};var r,n,a=function(e,t){if(null==e)return{};var r,n,a={},o=Object.keys(e);for(n=0;n<o.length;n++)r=o[n],t.indexOf(r)>=0||(a[r]=e[r]);return a}(e,t);if(Object.getOwnPropertySymbols){var o=Object.getOwnPropertySymbols(e);for(n=0;n<o.length;n++)r=o[n],t.indexOf(r)>=0||Object.prototype.propertyIsEnumerable.call(e,r)&&(a[r]=e[r])}return a}var u=a.a.createContext({}),p=function(e){var t=a.a.useContext(u),r=t;return e&&(r="function"==typeof e?e(t):c(c({},t),e)),r},l={inlineCode:"code",wrapper:function(e){var t=e.children;return a.a.createElement(a.a.Fragment,{},t)}},d=a.a.forwardRef((function(e,t){var r=e.components,n=e.mdxType,o=e.originalType,i=e.parentName,u=s(e,["components","mdxType","originalType","parentName"]),d=p(r),m=n,b=d["".concat(i,".").concat(m)]||d[m]||l[m]||o;return r?a.a.createElement(b,c(c({ref:t},u),{},{components:r})):a.a.createElement(b,c({ref:t},u))}));function m(e,t){var r=arguments,n=t&&t.mdxType;if("string"==typeof e||n){var o=r.length,i=new Array(o);i[0]=d;var c={};for(var s in t)hasOwnProperty.call(t,s)&&(c[s]=t[s]);c.originalType=e,c.mdxType="string"==typeof e?e:n,i[1]=c;for(var u=2;u<o;u++)i[u]=r[u];return a.a.createElement.apply(null,i)}return a.a.createElement.apply(null,r)}d.displayName="MDXCreateElement"},79:function(e,t,r){"use strict";r.r(t),r.d(t,"frontMatter",(function(){return i})),r.d(t,"metadata",(function(){return c})),r.d(t,"rightToc",(function(){return s})),r.d(t,"default",(function(){return p}));var n=r(2),a=r(6),o=(r(0),r(115)),i={id:"nutsPath",title:"Nuts Path",sidebar_label:"Nuts Path"},c={unversionedId:"dev/nutsPath",id:"dev/nutsPath",isDocsHomePage:!1,title:"Nuts Path",description:"nuts introduces a concept very similar to java's URL but with better extension builtin mechanisms and helper methods : `NutsPath`",source:"@site/docs/dev/nuts-path.md",permalink:"/nuts/docs/dev/nutsPath",editUrl:"https://github.com/thevpc/nuts/edit/master/website/docs/dev/nuts-path.md",sidebar_label:"Nuts Path",sidebar:"someSidebar",previous:{title:"Nuts Descriptor Integration",permalink:"/nuts/docs/dev/nutsDescriptorIntegration"},next:{title:"Nuts Stream",permalink:"/nuts/docs/dev/nutsStream"}},s=[],u={rightToc:s};function p(e){var t=e.components,r=Object(a.a)(e,["components"]);return Object(o.a)("wrapper",Object(n.a)({},u,r,{components:t,mdxType:"MDXLayout"}),Object(o.a)("p",null,Object(o.a)("strong",{parentName:"p"},"nuts")," introduces a concept very similar to java's URL but with better extension builtin mechanisms and helper methods : ",Object(o.a)("inlineCode",{parentName:"p"},"NutsPath")),Object(o.a)("p",null,"supported formats/protocols are:"),Object(o.a)("ul",null,Object(o.a)("li",{parentName:"ul"},"file format   ",Object(o.a)("inlineCode",{parentName:"li"},"/path/to/to/resource")," or ",Object(o.a)("inlineCode",{parentName:"li"},"c:\\\\path\\\\to\\\\resource")),Object(o.a)("li",{parentName:"ul"},"file URL ",Object(o.a)("inlineCode",{parentName:"li"},"file:/path/to/to/resource")," or ",Object(o.a)("inlineCode",{parentName:"li"},"file:c:/path/to/resource")),Object(o.a)("li",{parentName:"ul"},"http/https URLs (or any other Java supported URL) ",Object(o.a)("inlineCode",{parentName:"li"},"http://some-url")," or ",Object(o.a)("inlineCode",{parentName:"li"},"https://some-url")),Object(o.a)("li",{parentName:"ul"},"classpath ",Object(o.a)("inlineCode",{parentName:"li"},"classpath:/path/to/to/resource")," (you must provide the used classpath upon creation)"),Object(o.a)("li",{parentName:"ul"},"resource Path ",Object(o.a)("inlineCode",{parentName:"li"},"nuts-resource://groupId1:artifactId1#version1;groupId2:artifactId2#version2;/path/to/resource")," or ",Object(o.a)("inlineCode",{parentName:"li"},"nuts-resource://(groupId1:artifactId1#version1;groupId2:artifactId2#version2)/path/to/resource")," in that case the resource is lookup in any of the artifact classpaths (including dependencies)  ")))}p.isMDXComponent=!0}}]);