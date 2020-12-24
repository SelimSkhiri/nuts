(window.webpackJsonp=window.webpackJsonp||[]).push([[45],{112:function(e,t,a){"use strict";a.d(t,"a",(function(){return l})),a.d(t,"b",(function(){return O}));var n=a(0),s=a.n(n);function r(e,t,a){return t in e?Object.defineProperty(e,t,{value:a,enumerable:!0,configurable:!0,writable:!0}):e[t]=a,e}function b(e,t){var a=Object.keys(e);if(Object.getOwnPropertySymbols){var n=Object.getOwnPropertySymbols(e);t&&(n=n.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),a.push.apply(a,n)}return a}function i(e){for(var t=1;t<arguments.length;t++){var a=null!=arguments[t]?arguments[t]:{};t%2?b(Object(a),!0).forEach((function(t){r(e,t,a[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(a)):b(Object(a)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(a,t))}))}return e}function o(e,t){if(null==e)return{};var a,n,s=function(e,t){if(null==e)return{};var a,n,s={},r=Object.keys(e);for(n=0;n<r.length;n++)a=r[n],t.indexOf(a)>=0||(s[a]=e[a]);return s}(e,t);if(Object.getOwnPropertySymbols){var r=Object.getOwnPropertySymbols(e);for(n=0;n<r.length;n++)a=r[n],t.indexOf(a)>=0||Object.prototype.propertyIsEnumerable.call(e,a)&&(s[a]=e[a])}return s}var c=s.a.createContext({}),p=function(e){var t=s.a.useContext(c),a=t;return e&&(a="function"==typeof e?e(t):i(i({},t),e)),a},l=function(e){var t=p(e.components);return s.a.createElement(c.Provider,{value:t},e.children)},j={inlineCode:"code",wrapper:function(e){var t=e.children;return s.a.createElement(s.a.Fragment,{},t)}},u=s.a.forwardRef((function(e,t){var a=e.components,n=e.mdxType,r=e.originalType,b=e.parentName,c=o(e,["components","mdxType","originalType","parentName"]),l=p(a),u=n,O=l["".concat(b,".").concat(u)]||l[u]||j[u]||r;return a?s.a.createElement(O,i(i({ref:t},c),{},{components:a})):s.a.createElement(O,i({ref:t},c))}));function O(e,t){var a=arguments,n=t&&t.mdxType;if("string"==typeof e||n){var r=a.length,b=new Array(r);b[0]=u;var i={};for(var o in t)hasOwnProperty.call(t,o)&&(i[o]=t[o]);i.originalType=e,i.mdxType="string"==typeof e?e:n,b[1]=i;for(var c=2;c<r;c++)b[c]=a[c];return s.a.createElement.apply(null,b)}return s.a.createElement.apply(null,a)}u.displayName="MDXCreateElement"},98:function(e,t,a){"use strict";a.r(t),a.d(t,"frontMatter",(function(){return b})),a.d(t,"metadata",(function(){return i})),a.d(t,"rightToc",(function(){return o})),a.d(t,"default",(function(){return p}));var n=a(2),s=a(6),r=(a(0),a(112)),b={id:"javadoc_Extensions",title:"Extensions",sidebar_label:"Extensions"},i={unversionedId:"javadocs/javadoc_Extensions",id:"javadocs/javadoc_Extensions",isDocsHomePage:!1,title:"Extensions",description:"\u2615 NutsRepositoryModel",source:"@site/docs/javadocs/Extensions.md",permalink:"/nuts/docs/javadocs/javadoc_Extensions",editUrl:"https://github.com/facebook/docusaurus/edit/master/website/docs/javadocs/Extensions.md",sidebar_label:"Extensions",sidebar:"someSidebar",previous:{title:"Exceptions",permalink:"/nuts/docs/javadocs/javadoc_Exceptions"},next:{title:"Format",permalink:"/nuts/docs/javadocs/javadoc_Format"}},o=[{value:"\u2615 NutsRepositoryModel",id:"-nutsrepositorymodel",children:[{value:"\ud83d\uddd2 Instance Fields",id:"-instance-fields",children:[]},{value:"\ud83c\udf9b Instance Properties",id:"-instance-properties",children:[]},{value:"\u2699 Instance Methods",id:"-instance-methods",children:[]}]},{value:"\u2615 NutsWorkspaceExtension",id:"-nutsworkspaceextension",children:[{value:"\ud83c\udf9b Instance Properties",id:"-instance-properties-1",children:[]}]},{value:"\u2615 NutsWorkspaceExtensionManager",id:"-nutsworkspaceextensionmanager",children:[{value:"\u2699 Instance Methods",id:"-instance-methods-1",children:[]}]}],c={rightToc:o};function p(e){var t=e.components,a=Object(s.a)(e,["components"]);return Object(r.b)("wrapper",Object(n.a)({},c,a,{components:t,mdxType:"MDXLayout"}),Object(r.b)("h2",{id:"-nutsrepositorymodel"},"\u2615 NutsRepositoryModel"),Object(r.b)("pre",null,Object(r.b)("code",Object(n.a)({parentName:"pre"},{className:"language-java"}),"public  class net.thevpc.nuts.NutsRepositoryModel\n")),Object(r.b)("h3",{id:"-instance-fields"},"\ud83d\uddd2 Instance Fields"),Object(r.b)("h4",{id:"-cache"},"\ud83d\uddd2 CACHE"),Object(r.b)("pre",null,Object(r.b)("code",Object(n.a)({parentName:"pre"},{className:"language-java"})," int CACHE = CACHE_READ | CACHE_WRITE\n")),Object(r.b)("h4",{id:"-cache_read"},"\ud83d\uddd2 CACHE_READ"),Object(r.b)("pre",null,Object(r.b)("code",Object(n.a)({parentName:"pre"},{className:"language-java"})," int CACHE_READ = 16\n")),Object(r.b)("h4",{id:"-cache_write"},"\ud83d\uddd2 CACHE_WRITE"),Object(r.b)("pre",null,Object(r.b)("code",Object(n.a)({parentName:"pre"},{className:"language-java"})," int CACHE_WRITE = 32\n")),Object(r.b)("h4",{id:"-lib"},"\ud83d\uddd2 LIB"),Object(r.b)("pre",null,Object(r.b)("code",Object(n.a)({parentName:"pre"},{className:"language-java"})," int LIB = LIB_READ | LIB_WRITE | LIB_OVERRIDE\n")),Object(r.b)("h4",{id:"-lib_override"},"\ud83d\uddd2 LIB_OVERRIDE"),Object(r.b)("pre",null,Object(r.b)("code",Object(n.a)({parentName:"pre"},{className:"language-java"})," int LIB_OVERRIDE = 8\n")),Object(r.b)("h4",{id:"-lib_read"},"\ud83d\uddd2 LIB_READ"),Object(r.b)("pre",null,Object(r.b)("code",Object(n.a)({parentName:"pre"},{className:"language-java"})," int LIB_READ = 2\n")),Object(r.b)("h4",{id:"-lib_write"},"\ud83d\uddd2 LIB_WRITE"),Object(r.b)("pre",null,Object(r.b)("code",Object(n.a)({parentName:"pre"},{className:"language-java"})," int LIB_WRITE = 4\n")),Object(r.b)("h4",{id:"-mirroring"},"\ud83d\uddd2 MIRRORING"),Object(r.b)("pre",null,Object(r.b)("code",Object(n.a)({parentName:"pre"},{className:"language-java"})," int MIRRORING = 1\n")),Object(r.b)("h3",{id:"-instance-properties"},"\ud83c\udf9b Instance Properties"),Object(r.b)("h4",{id:"-deployorder"},"\ud83d\udcc4\ud83c\udf9b deployOrder"),Object(r.b)("pre",null,Object(r.b)("code",Object(n.a)({parentName:"pre"},{className:"language-java"}),"[read-only] default  int deployOrder\ndefault  int getDeployOrder()\n\n")),Object(r.b)("h4",{id:"-mode"},"\ud83d\udcc4\ud83c\udf9b mode"),Object(r.b)("pre",null,Object(r.b)("code",Object(n.a)({parentName:"pre"},{className:"language-java"}),"[read-only] default  int mode\ndefault  int getMode()\n\n")),Object(r.b)("h4",{id:"-name"},"\ud83d\udcc4\ud83c\udf9b name"),Object(r.b)("pre",null,Object(r.b)("code",Object(n.a)({parentName:"pre"},{className:"language-java"}),"[read-only]  String name\n String getName()\n\n")),Object(r.b)("h4",{id:"-repositorytype"},"\ud83d\udcc4\ud83c\udf9b repositoryType"),Object(r.b)("pre",null,Object(r.b)("code",Object(n.a)({parentName:"pre"},{className:"language-java"}),"[read-only] default  String repositoryType\ndefault  String getRepositoryType()\n\n")),Object(r.b)("h4",{id:"-speed"},"\ud83d\udcc4\ud83c\udf9b speed"),Object(r.b)("pre",null,Object(r.b)("code",Object(n.a)({parentName:"pre"},{className:"language-java"}),"[read-only] default  int speed\ndefault  int getSpeed()\n\n")),Object(r.b)("h4",{id:"-storelocationstrategy"},"\ud83d\udcc4\ud83c\udf9b storeLocationStrategy"),Object(r.b)("pre",null,Object(r.b)("code",Object(n.a)({parentName:"pre"},{className:"language-java"}),"[read-only] default  NutsStoreLocationStrategy storeLocationStrategy\ndefault  NutsStoreLocationStrategy getStoreLocationStrategy()\n\n")),Object(r.b)("h4",{id:"-uuid"},"\ud83d\udcc4\ud83c\udf9b uuid"),Object(r.b)("pre",null,Object(r.b)("code",Object(n.a)({parentName:"pre"},{className:"language-java"}),"[read-only] default  String uuid\ndefault  String getUuid()\n\n")),Object(r.b)("h3",{id:"-instance-methods"},"\u2699 Instance Methods"),Object(r.b)("h4",{id:"-acceptdeployid-mode-repository-session"},"\u2699 acceptDeploy(id, mode, repository, session)"),Object(r.b)("pre",null,Object(r.b)("code",Object(n.a)({parentName:"pre"},{className:"language-java"}),"boolean acceptDeploy(NutsId id, NutsFetchMode mode, NutsRepository repository, NutsSession session)\n")),Object(r.b)("ul",null,Object(r.b)("li",{parentName:"ul"},Object(r.b)("p",{parentName:"li"},Object(r.b)("strong",{parentName:"p"},"NutsId id")," : ")),Object(r.b)("li",{parentName:"ul"},Object(r.b)("p",{parentName:"li"},Object(r.b)("strong",{parentName:"p"},"NutsFetchMode mode")," : ")),Object(r.b)("li",{parentName:"ul"},Object(r.b)("p",{parentName:"li"},Object(r.b)("strong",{parentName:"p"},"NutsRepository repository")," : ")),Object(r.b)("li",{parentName:"ul"},Object(r.b)("p",{parentName:"li"},Object(r.b)("strong",{parentName:"p"},"NutsSession session")," : "))),Object(r.b)("h4",{id:"-acceptfetchid-mode-repository-session"},"\u2699 acceptFetch(id, mode, repository, session)"),Object(r.b)("pre",null,Object(r.b)("code",Object(n.a)({parentName:"pre"},{className:"language-java"}),"boolean acceptFetch(NutsId id, NutsFetchMode mode, NutsRepository repository, NutsSession session)\n")),Object(r.b)("ul",null,Object(r.b)("li",{parentName:"ul"},Object(r.b)("p",{parentName:"li"},Object(r.b)("strong",{parentName:"p"},"NutsId id")," : ")),Object(r.b)("li",{parentName:"ul"},Object(r.b)("p",{parentName:"li"},Object(r.b)("strong",{parentName:"p"},"NutsFetchMode mode")," : ")),Object(r.b)("li",{parentName:"ul"},Object(r.b)("p",{parentName:"li"},Object(r.b)("strong",{parentName:"p"},"NutsRepository repository")," : ")),Object(r.b)("li",{parentName:"ul"},Object(r.b)("p",{parentName:"li"},Object(r.b)("strong",{parentName:"p"},"NutsSession session")," : "))),Object(r.b)("h4",{id:"-fetchcontentid-descriptor-localpath-fetchmode-repository-session"},"\u2699 fetchContent(id, descriptor, localPath, fetchMode, repository, session)"),Object(r.b)("pre",null,Object(r.b)("code",Object(n.a)({parentName:"pre"},{className:"language-java"}),"NutsContent fetchContent(NutsId id, NutsDescriptor descriptor, Path localPath, NutsFetchMode fetchMode, NutsRepository repository, NutsSession session)\n")),Object(r.b)("ul",null,Object(r.b)("li",{parentName:"ul"},Object(r.b)("p",{parentName:"li"},Object(r.b)("strong",{parentName:"p"},"NutsId id")," : ")),Object(r.b)("li",{parentName:"ul"},Object(r.b)("p",{parentName:"li"},Object(r.b)("strong",{parentName:"p"},"NutsDescriptor descriptor")," : ")),Object(r.b)("li",{parentName:"ul"},Object(r.b)("p",{parentName:"li"},Object(r.b)("strong",{parentName:"p"},"Path localPath")," : ")),Object(r.b)("li",{parentName:"ul"},Object(r.b)("p",{parentName:"li"},Object(r.b)("strong",{parentName:"p"},"NutsFetchMode fetchMode")," : ")),Object(r.b)("li",{parentName:"ul"},Object(r.b)("p",{parentName:"li"},Object(r.b)("strong",{parentName:"p"},"NutsRepository repository")," : ")),Object(r.b)("li",{parentName:"ul"},Object(r.b)("p",{parentName:"li"},Object(r.b)("strong",{parentName:"p"},"NutsSession session")," : "))),Object(r.b)("h4",{id:"-fetchdescriptorid-fetchmode-repository-session"},"\u2699 fetchDescriptor(id, fetchMode, repository, session)"),Object(r.b)("pre",null,Object(r.b)("code",Object(n.a)({parentName:"pre"},{className:"language-java"}),"NutsDescriptor fetchDescriptor(NutsId id, NutsFetchMode fetchMode, NutsRepository repository, NutsSession session)\n")),Object(r.b)("ul",null,Object(r.b)("li",{parentName:"ul"},Object(r.b)("p",{parentName:"li"},Object(r.b)("strong",{parentName:"p"},"NutsId id")," : ")),Object(r.b)("li",{parentName:"ul"},Object(r.b)("p",{parentName:"li"},Object(r.b)("strong",{parentName:"p"},"NutsFetchMode fetchMode")," : ")),Object(r.b)("li",{parentName:"ul"},Object(r.b)("p",{parentName:"li"},Object(r.b)("strong",{parentName:"p"},"NutsRepository repository")," : ")),Object(r.b)("li",{parentName:"ul"},Object(r.b)("p",{parentName:"li"},Object(r.b)("strong",{parentName:"p"},"NutsSession session")," : "))),Object(r.b)("h4",{id:"-searchfilter-roots-fetchmode-repository-session"},"\u2699 search(filter, roots, fetchMode, repository, session)"),Object(r.b)("pre",null,Object(r.b)("code",Object(n.a)({parentName:"pre"},{className:"language-java"}),"Iterator\\<NutsId\\> search(NutsIdFilter filter, String[] roots, NutsFetchMode fetchMode, NutsRepository repository, NutsSession session)\n")),Object(r.b)("ul",null,Object(r.b)("li",{parentName:"ul"},Object(r.b)("p",{parentName:"li"},Object(r.b)("strong",{parentName:"p"},"NutsIdFilter filter")," : ")),Object(r.b)("li",{parentName:"ul"},Object(r.b)("p",{parentName:"li"},Object(r.b)("strong",{parentName:"p"},"String[] roots")," : ")),Object(r.b)("li",{parentName:"ul"},Object(r.b)("p",{parentName:"li"},Object(r.b)("strong",{parentName:"p"},"NutsFetchMode fetchMode")," : ")),Object(r.b)("li",{parentName:"ul"},Object(r.b)("p",{parentName:"li"},Object(r.b)("strong",{parentName:"p"},"NutsRepository repository")," : ")),Object(r.b)("li",{parentName:"ul"},Object(r.b)("p",{parentName:"li"},Object(r.b)("strong",{parentName:"p"},"NutsSession session")," : "))),Object(r.b)("h4",{id:"-searchlatestversionid-filter-fetchmode-repository-session"},"\u2699 searchLatestVersion(id, filter, fetchMode, repository, session)"),Object(r.b)("pre",null,Object(r.b)("code",Object(n.a)({parentName:"pre"},{className:"language-java"}),"NutsId searchLatestVersion(NutsId id, NutsIdFilter filter, NutsFetchMode fetchMode, NutsRepository repository, NutsSession session)\n")),Object(r.b)("ul",null,Object(r.b)("li",{parentName:"ul"},Object(r.b)("p",{parentName:"li"},Object(r.b)("strong",{parentName:"p"},"NutsId id")," : ")),Object(r.b)("li",{parentName:"ul"},Object(r.b)("p",{parentName:"li"},Object(r.b)("strong",{parentName:"p"},"NutsIdFilter filter")," : ")),Object(r.b)("li",{parentName:"ul"},Object(r.b)("p",{parentName:"li"},Object(r.b)("strong",{parentName:"p"},"NutsFetchMode fetchMode")," : ")),Object(r.b)("li",{parentName:"ul"},Object(r.b)("p",{parentName:"li"},Object(r.b)("strong",{parentName:"p"},"NutsRepository repository")," : ")),Object(r.b)("li",{parentName:"ul"},Object(r.b)("p",{parentName:"li"},Object(r.b)("strong",{parentName:"p"},"NutsSession session")," : "))),Object(r.b)("h4",{id:"-searchversionsid-idfilter-fetchmode-repository-session"},"\u2699 searchVersions(id, idFilter, fetchMode, repository, session)"),Object(r.b)("pre",null,Object(r.b)("code",Object(n.a)({parentName:"pre"},{className:"language-java"}),"Iterator\\<NutsId\\> searchVersions(NutsId id, NutsIdFilter idFilter, NutsFetchMode fetchMode, NutsRepository repository, NutsSession session)\n")),Object(r.b)("ul",null,Object(r.b)("li",{parentName:"ul"},Object(r.b)("p",{parentName:"li"},Object(r.b)("strong",{parentName:"p"},"NutsId id")," : ")),Object(r.b)("li",{parentName:"ul"},Object(r.b)("p",{parentName:"li"},Object(r.b)("strong",{parentName:"p"},"NutsIdFilter idFilter")," : ")),Object(r.b)("li",{parentName:"ul"},Object(r.b)("p",{parentName:"li"},Object(r.b)("strong",{parentName:"p"},"NutsFetchMode fetchMode")," : ")),Object(r.b)("li",{parentName:"ul"},Object(r.b)("p",{parentName:"li"},Object(r.b)("strong",{parentName:"p"},"NutsRepository repository")," : ")),Object(r.b)("li",{parentName:"ul"},Object(r.b)("p",{parentName:"li"},Object(r.b)("strong",{parentName:"p"},"NutsSession session")," : "))),Object(r.b)("h4",{id:"-updatestatisticsrepository-session"},"\u2699 updateStatistics(repository, session)"),Object(r.b)("pre",null,Object(r.b)("code",Object(n.a)({parentName:"pre"},{className:"language-java"}),"void updateStatistics(NutsRepository repository, NutsSession session)\n")),Object(r.b)("ul",null,Object(r.b)("li",{parentName:"ul"},Object(r.b)("p",{parentName:"li"},Object(r.b)("strong",{parentName:"p"},"NutsRepository repository")," : ")),Object(r.b)("li",{parentName:"ul"},Object(r.b)("p",{parentName:"li"},Object(r.b)("strong",{parentName:"p"},"NutsSession session")," : "))),Object(r.b)("h2",{id:"-nutsworkspaceextension"},"\u2615 NutsWorkspaceExtension"),Object(r.b)("pre",null,Object(r.b)("code",Object(n.a)({parentName:"pre"},{className:"language-java"}),"public  class net.thevpc.nuts.NutsWorkspaceExtension\n")),Object(r.b)("p",null,"Created by vpc on 1/15/17."),Object(r.b)("h3",{id:"-instance-properties-1"},"\ud83c\udf9b Instance Properties"),Object(r.b)("h4",{id:"-id"},"\ud83d\udcc4\ud83c\udf9b id"),Object(r.b)("p",null,"extension id pattern (configured)"),Object(r.b)("pre",null,Object(r.b)("code",Object(n.a)({parentName:"pre"},{className:"language-java"}),"[read-only]  NutsId id\n NutsId getId()\n\n")),Object(r.b)("h4",{id:"-wiredid"},"\ud83d\udcc4\ud83c\udf9b wiredId"),Object(r.b)("p",null,"extension id resolved and wired"),Object(r.b)("pre",null,Object(r.b)("code",Object(n.a)({parentName:"pre"},{className:"language-java"}),"[read-only]  NutsId wiredId\n NutsId getWiredId()\n\n")),Object(r.b)("h2",{id:"-nutsworkspaceextensionmanager"},"\u2615 NutsWorkspaceExtensionManager"),Object(r.b)("pre",null,Object(r.b)("code",Object(n.a)({parentName:"pre"},{className:"language-java"}),"public  class net.thevpc.nuts.NutsWorkspaceExtensionManager\n")),Object(r.b)("h3",{id:"-instance-methods-1"},"\u2699 Instance Methods"),Object(r.b)("h4",{id:"-createalltype-session"},"\u2699 createAll(type, session)"),Object(r.b)("pre",null,Object(r.b)("code",Object(n.a)({parentName:"pre"},{className:"language-java"}),"List\\<T\\> createAll(Class\\<T\\> type, NutsSession session)\n")),Object(r.b)("ul",null,Object(r.b)("li",{parentName:"ul"},Object(r.b)("p",{parentName:"li"},Object(r.b)("strong",{parentName:"p"},"Class\\<T",">"," type")," : ")),Object(r.b)("li",{parentName:"ul"},Object(r.b)("p",{parentName:"li"},Object(r.b)("strong",{parentName:"p"},"NutsSession session")," : "))),Object(r.b)("h4",{id:"-createallsupportedtype-supportcriteria-session"},"\u2699 createAllSupported(type, supportCriteria, session)"),Object(r.b)("pre",null,Object(r.b)("code",Object(n.a)({parentName:"pre"},{className:"language-java"}),"List\\<T\\> createAllSupported(Class\\<T\\> type, V supportCriteria, NutsSession session)\n")),Object(r.b)("ul",null,Object(r.b)("li",{parentName:"ul"},Object(r.b)("p",{parentName:"li"},Object(r.b)("strong",{parentName:"p"},"Class\\<T",">"," type")," : ")),Object(r.b)("li",{parentName:"ul"},Object(r.b)("p",{parentName:"li"},Object(r.b)("strong",{parentName:"p"},"V supportCriteria")," : ")),Object(r.b)("li",{parentName:"ul"},Object(r.b)("p",{parentName:"li"},Object(r.b)("strong",{parentName:"p"},"NutsSession session")," : "))),Object(r.b)("h4",{id:"-createserviceloaderservicetype-criteriatype-session"},"\u2699 createServiceLoader(serviceType, criteriaType, session)"),Object(r.b)("pre",null,Object(r.b)("code",Object(n.a)({parentName:"pre"},{className:"language-java"}),"NutsServiceLoader\\<T,B\\> createServiceLoader(Class\\<T\\> serviceType, Class\\<B\\> criteriaType, NutsSession session)\n")),Object(r.b)("ul",null,Object(r.b)("li",{parentName:"ul"},Object(r.b)("p",{parentName:"li"},Object(r.b)("strong",{parentName:"p"},"Class\\<T",">"," serviceType")," : ")),Object(r.b)("li",{parentName:"ul"},Object(r.b)("p",{parentName:"li"},Object(r.b)("strong",{parentName:"p"},"Class\\<B",">"," criteriaType")," : ")),Object(r.b)("li",{parentName:"ul"},Object(r.b)("p",{parentName:"li"},Object(r.b)("strong",{parentName:"p"},"NutsSession session")," : "))),Object(r.b)("h4",{id:"-createserviceloaderservicetype-criteriatype-classloader-session"},"\u2699 createServiceLoader(serviceType, criteriaType, classLoader, session)"),Object(r.b)("pre",null,Object(r.b)("code",Object(n.a)({parentName:"pre"},{className:"language-java"}),"NutsServiceLoader\\<T,B\\> createServiceLoader(Class\\<T\\> serviceType, Class\\<B\\> criteriaType, ClassLoader classLoader, NutsSession session)\n")),Object(r.b)("ul",null,Object(r.b)("li",{parentName:"ul"},Object(r.b)("p",{parentName:"li"},Object(r.b)("strong",{parentName:"p"},"Class\\<T",">"," serviceType")," : ")),Object(r.b)("li",{parentName:"ul"},Object(r.b)("p",{parentName:"li"},Object(r.b)("strong",{parentName:"p"},"Class\\<B",">"," criteriaType")," : ")),Object(r.b)("li",{parentName:"ul"},Object(r.b)("p",{parentName:"li"},Object(r.b)("strong",{parentName:"p"},"ClassLoader classLoader")," : ")),Object(r.b)("li",{parentName:"ul"},Object(r.b)("p",{parentName:"li"},Object(r.b)("strong",{parentName:"p"},"NutsSession session")," : "))),Object(r.b)("h4",{id:"-createsupportedtype-supportcriteria-session"},"\u2699 createSupported(type, supportCriteria, session)"),Object(r.b)("p",null,"create supported extension implementation or return null."),Object(r.b)("pre",null,Object(r.b)("code",Object(n.a)({parentName:"pre"},{className:"language-java"}),"T createSupported(Class\\<T\\> type, V supportCriteria, NutsSession session)\n")),Object(r.b)("ul",null,Object(r.b)("li",{parentName:"ul"},Object(r.b)("p",{parentName:"li"},Object(r.b)("strong",{parentName:"p"},"Class\\<T",">"," type")," : extension type")),Object(r.b)("li",{parentName:"ul"},Object(r.b)("p",{parentName:"li"},Object(r.b)("strong",{parentName:"p"},"V supportCriteria")," : context")),Object(r.b)("li",{parentName:"ul"},Object(r.b)("p",{parentName:"li"},Object(r.b)("strong",{parentName:"p"},"NutsSession session")," : session"))),Object(r.b)("h4",{id:"-createsupportedtype-supportcriteria-constructorparametertypes-constructorparameters-session"},"\u2699 createSupported(type, supportCriteria, constructorParameterTypes, constructorParameters, session)"),Object(r.b)("p",null,"create supported extension implementation or return null."),Object(r.b)("pre",null,Object(r.b)("code",Object(n.a)({parentName:"pre"},{className:"language-java"}),"T createSupported(Class\\<T\\> type, V supportCriteria, Class[] constructorParameterTypes, Object[] constructorParameters, NutsSession session)\n")),Object(r.b)("ul",null,Object(r.b)("li",{parentName:"ul"},Object(r.b)("p",{parentName:"li"},Object(r.b)("strong",{parentName:"p"},"Class\\<T",">"," type")," : extension type")),Object(r.b)("li",{parentName:"ul"},Object(r.b)("p",{parentName:"li"},Object(r.b)("strong",{parentName:"p"},"V supportCriteria")," : context")),Object(r.b)("li",{parentName:"ul"},Object(r.b)("p",{parentName:"li"},Object(r.b)("strong",{parentName:"p"},"Class[] constructorParameterTypes")," : constructor Parameter Types")),Object(r.b)("li",{parentName:"ul"},Object(r.b)("p",{parentName:"li"},Object(r.b)("strong",{parentName:"p"},"Object[] constructorParameters")," : constructor Parameters")),Object(r.b)("li",{parentName:"ul"},Object(r.b)("p",{parentName:"li"},Object(r.b)("strong",{parentName:"p"},"NutsSession session")," : session"))),Object(r.b)("h4",{id:"-discovertypesid-classloader-session"},"\u2699 discoverTypes(id, classLoader, session)"),Object(r.b)("pre",null,Object(r.b)("code",Object(n.a)({parentName:"pre"},{className:"language-java"}),"Set\\<Class\\> discoverTypes(NutsId id, ClassLoader classLoader, NutsSession session)\n")),Object(r.b)("ul",null,Object(r.b)("li",{parentName:"ul"},Object(r.b)("p",{parentName:"li"},Object(r.b)("strong",{parentName:"p"},"NutsId id")," : ")),Object(r.b)("li",{parentName:"ul"},Object(r.b)("p",{parentName:"li"},Object(r.b)("strong",{parentName:"p"},"ClassLoader classLoader")," : ")),Object(r.b)("li",{parentName:"ul"},Object(r.b)("p",{parentName:"li"},Object(r.b)("strong",{parentName:"p"},"NutsSession session")," : "))),Object(r.b)("h4",{id:"-getconfigextensionssession"},"\u2699 getConfigExtensions(session)"),Object(r.b)("p",null,"return loaded extensions"),Object(r.b)("pre",null,Object(r.b)("code",Object(n.a)({parentName:"pre"},{className:"language-java"}),"List\\<NutsId\\> getConfigExtensions(NutsSession session)\n")),Object(r.b)("ul",null,Object(r.b)("li",{parentName:"ul"},Object(r.b)("strong",{parentName:"li"},"NutsSession session")," : session")),Object(r.b)("h4",{id:"-getextensionobjectsextensionpoint-session"},"\u2699 getExtensionObjects(extensionPoint, session)"),Object(r.b)("pre",null,Object(r.b)("code",Object(n.a)({parentName:"pre"},{className:"language-java"}),"List\\<Object\\> getExtensionObjects(Class extensionPoint, NutsSession session)\n")),Object(r.b)("ul",null,Object(r.b)("li",{parentName:"ul"},Object(r.b)("p",{parentName:"li"},Object(r.b)("strong",{parentName:"p"},"Class extensionPoint")," : ")),Object(r.b)("li",{parentName:"ul"},Object(r.b)("p",{parentName:"li"},Object(r.b)("strong",{parentName:"p"},"NutsSession session")," : "))),Object(r.b)("h4",{id:"-getextensiontypesextensionpoint-session"},"\u2699 getExtensionTypes(extensionPoint, session)"),Object(r.b)("pre",null,Object(r.b)("code",Object(n.a)({parentName:"pre"},{className:"language-java"}),"Set\\<Class\\> getExtensionTypes(Class extensionPoint, NutsSession session)\n")),Object(r.b)("ul",null,Object(r.b)("li",{parentName:"ul"},Object(r.b)("p",{parentName:"li"},Object(r.b)("strong",{parentName:"p"},"Class extensionPoint")," : ")),Object(r.b)("li",{parentName:"ul"},Object(r.b)("p",{parentName:"li"},Object(r.b)("strong",{parentName:"p"},"NutsSession session")," : "))),Object(r.b)("h4",{id:"-getloadedextensionssession"},"\u2699 getLoadedExtensions(session)"),Object(r.b)("pre",null,Object(r.b)("code",Object(n.a)({parentName:"pre"},{className:"language-java"}),"List\\<NutsId\\> getLoadedExtensions(NutsSession session)\n")),Object(r.b)("ul",null,Object(r.b)("li",{parentName:"ul"},Object(r.b)("strong",{parentName:"li"},"NutsSession session")," : ")),Object(r.b)("h4",{id:"-installworkspaceextensioncomponentextensionpointtype-extensionimpl-session"},"\u2699 installWorkspaceExtensionComponent(extensionPointType, extensionImpl, session)"),Object(r.b)("pre",null,Object(r.b)("code",Object(n.a)({parentName:"pre"},{className:"language-java"}),"boolean installWorkspaceExtensionComponent(Class extensionPointType, Object extensionImpl, NutsSession session)\n")),Object(r.b)("ul",null,Object(r.b)("li",{parentName:"ul"},Object(r.b)("p",{parentName:"li"},Object(r.b)("strong",{parentName:"p"},"Class extensionPointType")," : ")),Object(r.b)("li",{parentName:"ul"},Object(r.b)("p",{parentName:"li"},Object(r.b)("strong",{parentName:"p"},"Object extensionImpl")," : ")),Object(r.b)("li",{parentName:"ul"},Object(r.b)("p",{parentName:"li"},Object(r.b)("strong",{parentName:"p"},"NutsSession session")," : "))),Object(r.b)("h4",{id:"-isloadedextensionsid-session"},"\u2699 isLoadedExtensions(id, session)"),Object(r.b)("pre",null,Object(r.b)("code",Object(n.a)({parentName:"pre"},{className:"language-java"}),"boolean isLoadedExtensions(NutsId id, NutsSession session)\n")),Object(r.b)("ul",null,Object(r.b)("li",{parentName:"ul"},Object(r.b)("p",{parentName:"li"},Object(r.b)("strong",{parentName:"p"},"NutsId id")," : ")),Object(r.b)("li",{parentName:"ul"},Object(r.b)("p",{parentName:"li"},Object(r.b)("strong",{parentName:"p"},"NutsSession session")," : "))),Object(r.b)("h4",{id:"-isregisteredinstanceextensionpointtype-extensionimpl-session"},"\u2699 isRegisteredInstance(extensionPointType, extensionImpl, session)"),Object(r.b)("pre",null,Object(r.b)("code",Object(n.a)({parentName:"pre"},{className:"language-java"}),"boolean isRegisteredInstance(Class extensionPointType, Object extensionImpl, NutsSession session)\n")),Object(r.b)("ul",null,Object(r.b)("li",{parentName:"ul"},Object(r.b)("p",{parentName:"li"},Object(r.b)("strong",{parentName:"p"},"Class extensionPointType")," : ")),Object(r.b)("li",{parentName:"ul"},Object(r.b)("p",{parentName:"li"},Object(r.b)("strong",{parentName:"p"},"Object extensionImpl")," : ")),Object(r.b)("li",{parentName:"ul"},Object(r.b)("p",{parentName:"li"},Object(r.b)("strong",{parentName:"p"},"NutsSession session")," : "))),Object(r.b)("h4",{id:"-isregisteredtypeextensionpointtype-extensiontype-session"},"\u2699 isRegisteredType(extensionPointType, extensionType, session)"),Object(r.b)("pre",null,Object(r.b)("code",Object(n.a)({parentName:"pre"},{className:"language-java"}),"boolean isRegisteredType(Class extensionPointType, Class extensionType, NutsSession session)\n")),Object(r.b)("ul",null,Object(r.b)("li",{parentName:"ul"},Object(r.b)("p",{parentName:"li"},Object(r.b)("strong",{parentName:"p"},"Class extensionPointType")," : ")),Object(r.b)("li",{parentName:"ul"},Object(r.b)("p",{parentName:"li"},Object(r.b)("strong",{parentName:"p"},"Class extensionType")," : ")),Object(r.b)("li",{parentName:"ul"},Object(r.b)("p",{parentName:"li"},Object(r.b)("strong",{parentName:"p"},"NutsSession session")," : "))),Object(r.b)("h4",{id:"-isregisteredtypeextensionpointtype-name-session"},"\u2699 isRegisteredType(extensionPointType, name, session)"),Object(r.b)("pre",null,Object(r.b)("code",Object(n.a)({parentName:"pre"},{className:"language-java"}),"boolean isRegisteredType(Class extensionPointType, String name, NutsSession session)\n")),Object(r.b)("ul",null,Object(r.b)("li",{parentName:"ul"},Object(r.b)("p",{parentName:"li"},Object(r.b)("strong",{parentName:"p"},"Class extensionPointType")," : ")),Object(r.b)("li",{parentName:"ul"},Object(r.b)("p",{parentName:"li"},Object(r.b)("strong",{parentName:"p"},"String name")," : ")),Object(r.b)("li",{parentName:"ul"},Object(r.b)("p",{parentName:"li"},Object(r.b)("strong",{parentName:"p"},"NutsSession session")," : "))),Object(r.b)("h4",{id:"-loadextensionextension-session"},"\u2699 loadExtension(extension, session)"),Object(r.b)("pre",null,Object(r.b)("code",Object(n.a)({parentName:"pre"},{className:"language-java"}),"NutsWorkspaceExtensionManager loadExtension(NutsId extension, NutsSession session)\n")),Object(r.b)("ul",null,Object(r.b)("li",{parentName:"ul"},Object(r.b)("p",{parentName:"li"},Object(r.b)("strong",{parentName:"p"},"NutsId extension")," : ")),Object(r.b)("li",{parentName:"ul"},Object(r.b)("p",{parentName:"li"},Object(r.b)("strong",{parentName:"p"},"NutsSession session")," : "))),Object(r.b)("h4",{id:"-registerinstanceextensionpoint-implementation-session"},"\u2699 registerInstance(extensionPoint, implementation, session)"),Object(r.b)("pre",null,Object(r.b)("code",Object(n.a)({parentName:"pre"},{className:"language-java"}),"boolean registerInstance(Class\\<T\\> extensionPoint, T implementation, NutsSession session)\n")),Object(r.b)("ul",null,Object(r.b)("li",{parentName:"ul"},Object(r.b)("p",{parentName:"li"},Object(r.b)("strong",{parentName:"p"},"Class\\<T",">"," extensionPoint")," : ")),Object(r.b)("li",{parentName:"ul"},Object(r.b)("p",{parentName:"li"},Object(r.b)("strong",{parentName:"p"},"T implementation")," : ")),Object(r.b)("li",{parentName:"ul"},Object(r.b)("p",{parentName:"li"},Object(r.b)("strong",{parentName:"p"},"NutsSession session")," : "))),Object(r.b)("h4",{id:"-registertypeextensionpointtype-extensiontype-source-session"},"\u2699 registerType(extensionPointType, extensionType, source, session)"),Object(r.b)("pre",null,Object(r.b)("code",Object(n.a)({parentName:"pre"},{className:"language-java"}),"boolean registerType(Class extensionPointType, Class extensionType, NutsId source, NutsSession session)\n")),Object(r.b)("ul",null,Object(r.b)("li",{parentName:"ul"},Object(r.b)("p",{parentName:"li"},Object(r.b)("strong",{parentName:"p"},"Class extensionPointType")," : ")),Object(r.b)("li",{parentName:"ul"},Object(r.b)("p",{parentName:"li"},Object(r.b)("strong",{parentName:"p"},"Class extensionType")," : ")),Object(r.b)("li",{parentName:"ul"},Object(r.b)("p",{parentName:"li"},Object(r.b)("strong",{parentName:"p"},"NutsId source")," : ")),Object(r.b)("li",{parentName:"ul"},Object(r.b)("p",{parentName:"li"},Object(r.b)("strong",{parentName:"p"},"NutsSession session")," : "))),Object(r.b)("h4",{id:"-unloadextensionextension-session"},"\u2699 unloadExtension(extension, session)"),Object(r.b)("pre",null,Object(r.b)("code",Object(n.a)({parentName:"pre"},{className:"language-java"}),"NutsWorkspaceExtensionManager unloadExtension(NutsId extension, NutsSession session)\n")),Object(r.b)("ul",null,Object(r.b)("li",{parentName:"ul"},Object(r.b)("p",{parentName:"li"},Object(r.b)("strong",{parentName:"p"},"NutsId extension")," : ")),Object(r.b)("li",{parentName:"ul"},Object(r.b)("p",{parentName:"li"},Object(r.b)("strong",{parentName:"p"},"NutsSession session")," : "))))}p.isMDXComponent=!0}}]);