(window.webpackJsonp=window.webpackJsonp||[]).push([[21],{117:function(e,t,a){"use strict";a.d(t,"a",(function(){return d}));var n=a(0),r=a.n(n);function c(e,t,a){return t in e?Object.defineProperty(e,t,{value:a,enumerable:!0,configurable:!0,writable:!0}):e[t]=a,e}function o(e,t){var a=Object.keys(e);if(Object.getOwnPropertySymbols){var n=Object.getOwnPropertySymbols(e);t&&(n=n.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),a.push.apply(a,n)}return a}function s(e){for(var t=1;t<arguments.length;t++){var a=null!=arguments[t]?arguments[t]:{};t%2?o(Object(a),!0).forEach((function(t){c(e,t,a[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(a)):o(Object(a)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(a,t))}))}return e}function i(e,t){if(null==e)return{};var a,n,r=function(e,t){if(null==e)return{};var a,n,r={},c=Object.keys(e);for(n=0;n<c.length;n++)a=c[n],t.indexOf(a)>=0||(r[a]=e[a]);return r}(e,t);if(Object.getOwnPropertySymbols){var c=Object.getOwnPropertySymbols(e);for(n=0;n<c.length;n++)a=c[n],t.indexOf(a)>=0||Object.prototype.propertyIsEnumerable.call(e,a)&&(r[a]=e[a])}return r}var l=r.a.createContext({}),p=function(e){var t=r.a.useContext(l),a=t;return e&&(a="function"==typeof e?e(t):s(s({},t),e)),a},u={inlineCode:"code",wrapper:function(e){var t=e.children;return r.a.createElement(r.a.Fragment,{},t)}},m=r.a.forwardRef((function(e,t){var a=e.components,n=e.mdxType,c=e.originalType,o=e.parentName,l=i(e,["components","mdxType","originalType","parentName"]),m=p(a),d=n,h=m["".concat(o,".").concat(d)]||m[d]||u[d]||c;return a?r.a.createElement(h,s(s({ref:t},l),{},{components:a})):r.a.createElement(h,s({ref:t},l))}));function d(e,t){var a=arguments,n=t&&t.mdxType;if("string"==typeof e||n){var c=a.length,o=new Array(c);o[0]=m;var s={};for(var i in t)hasOwnProperty.call(t,i)&&(s[i]=t[i]);s.originalType=e,s.mdxType="string"==typeof e?e:n,o[1]=s;for(var l=2;l<c;l++)o[l]=a[l];return r.a.createElement.apply(null,o)}return r.a.createElement.apply(null,a)}m.displayName="MDXCreateElement"},76:function(e,t,a){"use strict";a.r(t),a.d(t,"frontMatter",(function(){return c})),a.d(t,"metadata",(function(){return o})),a.d(t,"rightToc",(function(){return s})),a.d(t,"default",(function(){return l}));var n=a(2),r=(a(0),a(117));const c={id:"search-cmds",title:"Search Command",sidebar_label:"Search Command"},o={unversionedId:"cmd/search-cmds",id:"cmd/search-cmds",isDocsHomePage:!1,title:"Search Command",description:"Artifact can be in multiple states. they can be",source:"@site/docs/cmd/serach-cmd.md",permalink:"/nuts/docs/cmd/search-cmds",editUrl:"https://github.com/thevpc/nuts/edit/master/website/docs/cmd/serach-cmd.md",sidebar_label:"Search Command",sidebar:"someSidebar",previous:{title:"Nuts Commands",permalink:"/nuts/docs/cmd/cmds"},next:{title:"Version Command",permalink:"/nuts/docs/cmd/version-cmd"}},s=[],i={rightToc:s};function l({components:e,...t}){return Object(r.a)("wrapper",Object(n.a)({},i,t,{components:e,mdxType:"MDXLayout"}),Object(r.a)("p",null,"Artifact can be in multiple states. they can be"),Object(r.a)("ul",null,Object(r.a)("li",{parentName:"ul"},"'unavailable' if no registered repository can serve that artifact"),Object(r.a)("li",{parentName:"ul"},"'available' if there is at least one repository that can serve that artifact"),Object(r.a)("li",{parentName:"ul"},"'fetched' if there is a repository that can serve the artifact from local machine. This happens either if the repository is a local one (for instance a folder repository) or the repository has already downloaded and cached the artifact"),Object(r.a)("li",{parentName:"ul"},"'installed' if the artifact is fetched and installed in the the machine."),Object(r.a)("li",{parentName:"ul"},"'installed default' if the artifact is installed and marked as default")),Object(r.a)("p",null,"To search for these artifacts status you will use the appropriate option flag with an artifact query.\nAn artifact query is a generalization of an artifact id where you may use wild cards and version intervals in it.\nThese are some examples of artifact queries."),Object(r.a)("pre",null,Object(r.a)("code",Object(n.a)({parentName:"pre"},{}),"# all artifacts that start with netbeans, whatever groupId they belong to\n# nuts search netbeans*\n\n# all artifacts that start with netbeans, whatever groupId they belong to. same as the latter.\n# nuts search *:netbeans*\n\n# all artifacts in the net.vpc.app groupId\n# nuts search net.vpc.*:*\n\n# all artifacts in the net.vpc.* groupId which includes all of net.vpc.app and net.vpc.app.example for instance.\n# nuts search net.vpc.*:*\n\n# all artifacts that start with netbeans and is available for windows operating system in x86_64 architecture\n# nuts search netbeans*?os=windows&arch=x86_64\n\n# all netbeans launcher version that are greater than 1.2.0 (excluding 1.2.0)\n# nuts search netbeans-launcher#]1.2.0,[\n\n# all netbeans launcher version that are greater than 1.2.0 (including 1.2.0)\n# nuts search netbeans-launcher#[1.2.0,[\n\n")),Object(r.a)("p",null,"You can then use the these flags to tighten your search :"),Object(r.a)("ul",null,Object(r.a)("li",{parentName:"ul"},"--installed (or -i) : search only for installed artifacts"),Object(r.a)("li",{parentName:"ul"},"--local     : search only for fetched artifacts"),Object(r.a)("li",{parentName:"ul"},"--remote    : search only for non fetched artifacts"),Object(r.a)("li",{parentName:"ul"},"--online    : search in installed then in local then in remote, stop when you first find a result."),Object(r.a)("li",{parentName:"ul"},"--anywhere  (or -a) : search in installed and local and remote, return all results.")),Object(r.a)("p",null,"You can also change the output layout using --long (or -l) flag"),Object(r.a)("pre",null,Object(r.a)("code",Object(n.a)({parentName:"pre"},{}),"me@linux:~> nuts search -i -l\nI-X 2019-08-26 09:53:53.141 anonymous vpc-public-maven net.vpc.app:netbeans-launcher#1.2.1\nIcX 2019-08-24 11:05:49.591 admin     maven-local      net.vpc.app.nuts.toolbox:nsh#0.8.3.0-rc1\nI-x 2019-08-26 09:50:03.423 anonymous vpc-public-maven net.vpc.app:kifkif#1.3.3\n")),Object(r.a)("p",null,"you can even change the output format"),Object(r.a)("pre",null,Object(r.a)("code",Object(n.a)({parentName:"pre"},{}),"me@linux:~> nuts search -i -l --json\n")),Object(r.a)("pre",null,Object(r.a)("code",Object(n.a)({parentName:"pre"},{className:"language-json"}),'[\n{\n  "id": "vpc-public-maven://net.vpc.app:netbeans-launcher#1.2.1",\n  "descriptor": {\n    "id": "net.vpc.app:netbeans-launcher#1.2.1",\n    "parents": [],\n    "packaging": "jar",\n    "executable": true,\n    ...\n  }\n }\n]\n')),Object(r.a)("p",null,"Indeed, all of ",Object(r.a)("strong",{parentName:"p"},"nuts")," commands support the following formats : ",Object(r.a)("strong",{parentName:"p"},"plain"),", ",Object(r.a)("strong",{parentName:"p"},"json"),", ",Object(r.a)("strong",{parentName:"p"},"xml"),", ",Object(r.a)("strong",{parentName:"p"},"table")," and ",Object(r.a)("strong",{parentName:"p"},"tree")," because ",Object(r.a)("strong",{parentName:"p"},"nuts")," adds support to multi format output by default. You can switch to any of them for any command by adding the right option in ",Object(r.a)("strong",{parentName:"p"},"nuts")," (typically --plain, --json, --xml, --table and --tree). I know this is awesome!."),Object(r.a)("p",null,Object(r.a)("strong",{parentName:"p"},"search"),' is a very versatile command, you are welcome to run "nuts search --help" to get more information.'))}l.isMDXComponent=!0}}]);