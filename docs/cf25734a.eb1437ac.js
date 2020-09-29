(window.webpackJsonp=window.webpackJsonp||[]).push([[50],{104:function(e,t,n){"use strict";n.r(t),n.d(t,"frontMatter",(function(){return r})),n.d(t,"metadata",(function(){return s})),n.d(t,"rightToc",(function(){return l})),n.d(t,"default",(function(){return p}));var a=n(2),o=n(6),i=(n(0),n(116)),r={id:"nutsAndMaven",title:"Nuts and Maven",sidebar_label:"Nuts and Maven"},s={unversionedId:"intro/nutsAndMaven",id:"intro/nutsAndMaven",isDocsHomePage:!1,title:"Nuts and Maven",description:"`",source:"@site/docs/intro/nuts-and-maven.md",permalink:"/nuts/docs/intro/nutsAndMaven",editUrl:"https://github.com/facebook/docusaurus/edit/master/website/docs/intro/nuts-and-maven.md",sidebar_label:"Nuts and Maven",sidebar:"someSidebar",previous:{title:"Introduction",permalink:"/nuts/docs/intro/introduction"},next:{title:"Repository Structure",permalink:"/nuts/docs/intro/projects"}},l=[{value:"Nuts Package manager",id:"nuts-package-manager",children:[{value:"Transitive dependency resolution manager",id:"transitive-dependency-resolution-manager",children:[]},{value:"Package manager",id:"package-manager",children:[]},{value:"Feature rich Toolset",id:"feature-rich-toolset",children:[]}]},{value:"Nuts Workspaces",id:"nuts-workspaces",children:[]},{value:"Application Framework",id:"application-framework",children:[]},{value:"Nuts ? Really ?",id:"nuts--really-",children:[]},{value:"Let&#39;s start the journey",id:"lets-start-the-journey",children:[]}],c={rightToc:l};function p(e){var t=e.components,n=Object(o.a)(e,["components"]);return Object(i.b)("wrapper",Object(a.a)({},c,n,{components:t,mdxType:"MDXLayout"}),Object(i.b)("pre",null,Object(i.b)("code",Object(a.a)({parentName:"pre"},{}),"     __        __    \n  /\\ \\ \\ _  __/ /______\n /  \\/ / / / / __/ ___/\n/ /\\  / /_/ / /_(__  )\n\\_\\ \\/\\__,_/\\__/____/    version vfile-version: unsupported file : /home/vpc/.config/nuts/nuts\nExecution Failed with code 2\n\n")),Object(i.b)("h1",{id:"youd-still-be-maven-yet-you-gonna-be-nuts"},"You'd still be Maven, yet you gonna be Nuts"),Object(i.b)("p",null,"Is there any package manager for Java(TM) applications? You can google for it and you will find that many have\nqueried this on blogs and forums. In most cases responses point to maven and gradle, the tremendous build tools. However,\nboth maven and gradle are build tools, while helping build packages they lack of deployment features. They bundle every\ndependency in every package (think of wars, ears and standalone jars). They do not handle installation or upgrading.\nApache ivy, as well, while competing with maven build tool does not provide more than transitive dependency management.",Object(i.b)("br",{parentName:"p"}),"\n","The main idea behind a package manager is the automation of installation, update, configuration and removal of programs\nor libraries in a coherent manner with the help of a database that manages binaries and metadata. maven, to consider one,\nsticks to the build process, an goes no further."),Object(i.b)("p",null,"You may also ask, \"Why ever, do we need a package manager for Java(TM) applications\". Okkay, let's take some\nexample of Java(TM) applications. How can we install apache netbeans IDE ? The proper way is to browse to the editor's\nwebsite, select the proper mirror if applicable, download the archive, uncompress it, chmod the main binary (i'm a linux\nguy) and adjust PATH environment variable to point to this binary; Quite a pain. What do we do to update it now? Hopefully,\nthe IDE has a solid plugin architecture and an in-app update/upgrade tool that will help the process (in a gui manner of\ncourse). The same applies to eclipse and apache tomcat with the exception that apache tomcat does not even bundle an in-app\nupdate tool. The same applies too when dealing with other operating systems (Windows, MacOS, ...). Managing Java(TM)\napplications is far from helpful."),Object(i.b)("p",null,"Furthermore, as Java(TM) applications are (usually) not bundled in OS-aware installers, you will end up with a spaguetty\nhome directory with applications installed all over your partitions, which - simply - does not mix up with all the work\nOS-developers have done to separate logs from data, from temporary files, from binaries, etc. Each application will handle\nit's files in a very specific manner that would make it hard to manage own's disk (automatic archive/backup/restore) or roaming\napplications across machines, etc."),Object(i.b)("p",null,"Moreover, in a world of containers and devops, deployments of Java(TM) applications need to be automatable and reproducible\nwith the highest level of simplicity, configurability and integrability. Installing tomcat on a custom port should not not\nbe as painful as using a custom Docker image or a complicated Dockerfile or even a custom apache tomcat bundle. "),Object(i.b)("p",null,"When we recall that Java(TM) is the one language that has the more versatile number of libraries, frameworks and tools,\nI find it annoying not to have a decent package manager to make the leap and provide facilities I find prime in other\nlanguages and platforms (",Object(i.b)("inlineCode",{parentName:"p"},"pip"),"/python, ",Object(i.b)("inlineCode",{parentName:"p"},"npm"),"/nodejs/javascript) and most of linux distribution (",Object(i.b)("inlineCode",{parentName:"p"},"zypper"),"/opsensuse, ",Object(i.b)("inlineCode",{parentName:"p"},"dnf"),"/redhat\n",Object(i.b)("inlineCode",{parentName:"p"},"apt-get"),"/debian/ubuntu)"),Object(i.b)("p",null,"Hence I'm introducing here a humble attempt to provide a tiny (300ko) yet powerful package manager for Java(TM)\napplications (but not only) that should handle jar files seamlessly (with little or no modification) and that comes with\na set of portable tools that makes this management at a higher level. I'm not talking about redefining the wheel.\nI'm aware that many tools such as maven, are already very good at what they do, I just needed to make the leap for deployments.\nYou will be able to deploy your applications without bundling all of their dependencies : ",Object(i.b)("strong",{parentName:"p"},Object(i.b)("inlineCode",{parentName:"strong"},"nuts"))," will take care of that. "),Object(i.b)("p",null,"So you'd still be maven, yet you gonna be ",Object(i.b)("strong",{parentName:"p"},Object(i.b)("inlineCode",{parentName:"strong"},"nuts")),"."),Object(i.b)("h2",{id:"nuts-package-manager"},"Nuts Package manager"),Object(i.b)("p",null,Object(i.b)("strong",{parentName:"p"},Object(i.b)("inlineCode",{parentName:"strong"},"nuts"))," is actually :"),Object(i.b)("ul",null,Object(i.b)("li",{parentName:"ul"},"a transitive dependency resolution manager"),Object(i.b)("li",{parentName:"ul"},"package manager (backports maven and supports maven repositories)"),Object(i.b)("li",{parentName:"ul"},"automation tool"),Object(i.b)("li",{parentName:"ul"},"feature rich toolset"),Object(i.b)("li",{parentName:"ul"},"application framework"),Object(i.b)("li",{parentName:"ul"},"decentralized"),Object(i.b)("li",{parentName:"ul"},"sandbox based")),Object(i.b)("h3",{id:"transitive-dependency-resolution-manager"},"Transitive dependency resolution manager"),Object(i.b)("p",null,Object(i.b)("strong",{parentName:"p"},Object(i.b)("inlineCode",{parentName:"strong"},"nuts"))," calculates transitive dependencies of an application to resolve other packages to download at install or\nupdate/upgrade time. So typically, deployed applications should no more bundle their dependencies within the deployed archive.\nThus we avoid the annoying fat jars (using maven plugins like 'maven-assembly-plugin' and 'maven-shade-plugin') and lib folders\n(using 'maven-dependency-plugin'). It will also reuse dependencies and packages across multiple installed applications\nand hence save disk space, and network bandwidth."),Object(i.b)("p",null,"All what ",Object(i.b)("strong",{parentName:"p"},Object(i.b)("inlineCode",{parentName:"strong"},"nuts"))," needs is a descriptor file withing the jar file that defines the immediate dependencies. It then\ncalculates all transitive dependencies automatically. And guess what, all maven built jars already contain that\ndescriptor : the pom.xml file. So basically all maven applications are already ",Object(i.b)("strong",{parentName:"p"},Object(i.b)("inlineCode",{parentName:"strong"},"nuts"))," aware applications."),Object(i.b)("h3",{id:"package-manager"},"Package manager"),Object(i.b)("p",null,Object(i.b)("strong",{parentName:"p"},Object(i.b)("inlineCode",{parentName:"strong"},"nuts"))," uses this dependency resolution to help install, update, remove and search for applications. To be able to use an\napplication, it has to be installed and configured with all of its dependencies. This is the main goal of ",Object(i.b)("strong",{parentName:"p"},Object(i.b)("inlineCode",{parentName:"strong"},"nuts")),".\nWhen we ask to install tomcat, for instance, it will search for the best version in registered repositories, download it,\nand configure it to be ready for execution. The best version is not always the the latest one. Actually it would be the\nlatest valid one, thus the latest one that matches some constraints.",Object(i.b)("br",{parentName:"p"}),"\n","Constraints include the version of the running java (tomcat 8 works on java 7 but not 6 for instance), the running operating\nsystem (windows, linux, ... to help selecting the proper binaries), may be the hardware architecture or even the\noperating distribution (for linux based systems). Constraints will filter the search result to include the best, the most\naccurate version to install. Installation also would configure the installed application and even may run another\nartifact to help this configuration."),Object(i.b)("p",null,Object(i.b)("strong",{parentName:"p"},Object(i.b)("inlineCode",{parentName:"strong"},"nuts"))," also handles search for newer versions and update the installed application at request. Updating a software does not\nnecessarily delete the older version. Both version can coexist and it is up to the user the decide whether or\nnot to retain both versions. Indeed, one of the key features of ",Object(i.b)("strong",{parentName:"p"},Object(i.b)("inlineCode",{parentName:"strong"},"nuts"))," is the ability to install and hence run multiple versions\nof the same software in parallel. You would never see an error message telling you can't install that software because a\ndependency of it is installed with different version. All software and all libraries can coexist peacefully."),Object(i.b)("p",null,"Software artifacts are stored in repositories. ",Object(i.b)("strong",{parentName:"p"},Object(i.b)("inlineCode",{parentName:"strong"},"nuts"))," can handle multiple repositories, remote and local ones.\nInstalled software are stored in special local repositories. Supported repositories include maven repositories and github\nrepositories. Actually a fresh installation of ",Object(i.b)("strong",{parentName:"p"},Object(i.b)("inlineCode",{parentName:"strong"},"nuts"))," is configured with maven central repository so that, you already have access\nto thousands of installable artifacts."),Object(i.b)("p",null,"At some point, you may need to uninstall an artifact and that's to undo the artifact installation.",Object(i.b)("br",{parentName:"p"}),"\n","Installation will help you choose between uninstalling binaries only and keeping data/config files or remove permanently\nall of the artifact files. In all ways, uninstalling will not affect other artifacts that use the same dependencies if ever. "),Object(i.b)("h3",{id:"feature-rich-toolset"},"Feature rich Toolset"),Object(i.b)("p",null,Object(i.b)("strong",{parentName:"p"},Object(i.b)("inlineCode",{parentName:"strong"},"nuts"))," is intended to be used either by human users or by robots and other applications. It comes with portable,\nfeature rich toolset, a versatile library and a handy parsable result. "),Object(i.b)("p",null,Object(i.b)("strong",{parentName:"p"},Object(i.b)("inlineCode",{parentName:"strong"},"nuts"))," is mainly a commandline program that helps installing, uninstalling, searching, updating and running artifacts.\nTo help desktop integration, ",Object(i.b)("strong",{parentName:"p"},Object(i.b)("inlineCode",{parentName:"strong"},"nuts"))," installs by default a set of other companion tools such as ",Object(i.b)("inlineCode",{parentName:"p"},"nsh")," (a portable\nbash-compatible implementation), ",Object(i.b)("inlineCode",{parentName:"p"},"nadmin")," (an administration tool for ",Object(i.b)("strong",{parentName:"p"},Object(i.b)("inlineCode",{parentName:"strong"},"nuts"))," to configure users, authorizations, repositories,\n...) and ",Object(i.b)("inlineCode",{parentName:"p"},"ndi")," (desktop integration) to help creating application shortcuts and scripts;"),Object(i.b)("p",null,Object(i.b)("strong",{parentName:"p"},"nsh")," brings the bash  facilities to all environments (windows included) in a very portable manner. Besides it integrates\nwell with the installed ",Object(i.b)("strong",{parentName:"p"},Object(i.b)("inlineCode",{parentName:"strong"},"nuts"))," version. Several common commands are ported to ",Object(i.b)("inlineCode",{parentName:"p"},"nsh")," such as ",Object(i.b)("inlineCode",{parentName:"p"},"cat"),",",Object(i.b)("inlineCode",{parentName:"p"},"head"),", and ",Object(i.b)("inlineCode",{parentName:"p"},"ssh"),",\nas well as core features like pipes, redirection and scripts. "),Object(i.b)("p",null,Object(i.b)("strong",{parentName:"p"},"nadmin")," is intended for configuring ",Object(i.b)("strong",{parentName:"p"},Object(i.b)("inlineCode",{parentName:"strong"},"nuts"))," workspaces, managing repositories and users. It helps also configuring\nsub commands and aliases to make ",Object(i.b)("strong",{parentName:"p"},Object(i.b)("inlineCode",{parentName:"strong"},"nuts"))," usage even easier."),Object(i.b)("p",null,Object(i.b)("strong",{parentName:"p"},"ndi"),", is the tool for a seamless integration in your operating system. It mainly configures user PATH variable environment and\ncreates scripts that point to your ",Object(i.b)("strong",{parentName:"p"},Object(i.b)("inlineCode",{parentName:"strong"},"nuts"))," installation on linux or MacOS systems. It creates shortcuts to a pre-configured environment\non windows."),Object(i.b)("h2",{id:"nuts-workspaces"},"Nuts Workspaces"),Object(i.b)("p",null,"One of the key features of ",Object(i.b)("strong",{parentName:"p"},Object(i.b)("inlineCode",{parentName:"strong"},"nuts"))," is the ability to support multiple isolated workspaces, each managing it's own\nrepositories, applications and libraries; each defining it's sandbox security constraints.\nThus non-root installation is made easy while it remains possible to overlap between workspaces by sharing repositories.\nRoaming is also supported, so that a workspaces can be copied/moved across machines."),Object(i.b)("h2",{id:"application-framework"},"Application Framework"),Object(i.b)("p",null,Object(i.b)("strong",{parentName:"p"},"Nuts")," can also be embedded as a library in you application. This enables you to wire classes on the fly by its network\ndependency-aware classloading mechanisms. The library allows as well building solid and well integrated applications,\nmainly console applications. Indeed, ",Object(i.b)("inlineCode",{parentName:"p"},"nuts")," comes with rich outputs that support automatic formatting to json, xml, table,\ntree and plain texts. It handles standard File Systems layouts; XDG Base Directory Specification is implemented\nfor linux and MacOS. A compatible one is also implemented in Windows systems. And of course, it helps seamlessly install,\nupdate and remove events. "),Object(i.b)("h2",{id:"nuts--really-"},"Nuts ? Really ?"),Object(i.b)("p",null,"In every palace you will find the wizard and the fool, the ",Object(i.b)("strong",{parentName:"p"},Object(i.b)("inlineCode",{parentName:"strong"},"maven"))," and the ",Object(i.b)("strong",{parentName:"p"},Object(i.b)("inlineCode",{parentName:"strong"},"nuts")),"; There's no\nexception in the java kingdom! If you do prefer acronyms here is another reason : ",Object(i.b)("strong",{parentName:"p"},Object(i.b)("inlineCode",{parentName:"strong"},"nuts"))," stands for Network\nUpdatable Things Services. It should be able to facilitate things deployment and update over the\nwire where things resolve here to any piece of software depending (or not) on other piece of software."),Object(i.b)("h2",{id:"lets-start-the-journey"},"Let's start the journey"),Object(i.b)("p",null,"we start by opening a new terminal (termm, konsole or whatever you prefer) then download ",Object(i.b)("strong",{parentName:"p"},Object(i.b)("inlineCode",{parentName:"strong"},"nuts"))," using this command :\nOn linux/MacOS system we issue :"),Object(i.b)("pre",null,Object(i.b)("code",Object(a.a)({parentName:"pre"},{}),"wget https://github.com/thevpc/vpc-public-maven/raw/master/net/vpc/app/nuts/nuts/file-version: unsupported file : /home/vpc/.config/nuts/nuts\nExecution Failed with code 2\n/nuts-file-version: unsupported file : /home/vpc/.config/nuts/nuts\nExecution Failed with code 2\n.jar\n")),Object(i.b)("p",null,"Let's check that java is installed :"),Object(i.b)("pre",null,Object(i.b)("code",Object(a.a)({parentName:"pre"},{}),"java --version\n")),Object(i.b)("p",null,"Now we run ",Object(i.b)("strong",{parentName:"p"},Object(i.b)("inlineCode",{parentName:"strong"},"nuts"))),Object(i.b)("pre",null,Object(i.b)("code",Object(a.a)({parentName:"pre"},{}),"java -jar nuts-file-version: unsupported file : /home/vpc/.config/nuts/nuts\nExecution Failed with code 2\n.jar -zy\n")),Object(i.b)("p",null,"We used the flags ",Object(i.b)("inlineCode",{parentName:"p"},"-y")," to auto-confirm and ",Object(i.b)("inlineCode",{parentName:"p"},"-z")," to ignore cached binaries (combined here as ",Object(i.b)("inlineCode",{parentName:"p"},"-zy"),").\nThese flags are not required. We use them here to make installation work in all cases.\nInstallation may last several minutes as it will download all required dependencies, companions and tools."),Object(i.b)("p",null,"You should then see this message"),Object(i.b)("pre",null,Object(i.b)("code",Object(a.a)({parentName:"pre"},{}),"Welcome to nuts. Yeah, it is working...\n")),Object(i.b)("p",null,Object(i.b)("strong",{parentName:"p"},Object(i.b)("inlineCode",{parentName:"strong"},"nuts"))," is well installed, just restart your terminal."),Object(i.b)("p",null,"Now we will install apache tomcat. So in your terminal type:"),Object(i.b)("pre",null,Object(i.b)("code",Object(a.a)({parentName:"pre"},{}),"nuts install ntomcat\nnuts ntomcat start --set-port 9090\n")),Object(i.b)("p",null,"The first two commands will install tomcat helper tool (ntomcat) that will download latest version of tomcat and configure it to 9090 port.\nThe last command will start tomcat.\nLet's check tomcat status now"),Object(i.b)("pre",null,Object(i.b)("code",Object(a.a)({parentName:"pre"},{}),"nuts tomcat status\n")),Object(i.b)("p",null,"Now we will do the same with derby database. We will install it and run it."),Object(i.b)("pre",null,Object(i.b)("code",Object(a.a)({parentName:"pre"},{}),"nuts install nderby\nnuts nderby start\n")),Object(i.b)("p",null,"As you can see, simple commands are all you need to download, install, configure and run tomcat or derby or any application that is deployed in the maven repository."),Object(i.b)("p",null,"So please visit ",Object(i.b)("inlineCode",{parentName:"p"},"nuts")," ",Object(i.b)("a",Object(a.a)({parentName:"p"},{href:"https://thevpc.github.com/nuts"}),"website")," or ",Object(i.b)("a",Object(a.a)({parentName:"p"},{href:"https://github.com/thevpc/nuts"}),"github repository")," for more information."))}p.isMDXComponent=!0},116:function(e,t,n){"use strict";n.d(t,"a",(function(){return d})),n.d(t,"b",(function(){return m}));var a=n(0),o=n.n(a);function i(e,t,n){return t in e?Object.defineProperty(e,t,{value:n,enumerable:!0,configurable:!0,writable:!0}):e[t]=n,e}function r(e,t){var n=Object.keys(e);if(Object.getOwnPropertySymbols){var a=Object.getOwnPropertySymbols(e);t&&(a=a.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),n.push.apply(n,a)}return n}function s(e){for(var t=1;t<arguments.length;t++){var n=null!=arguments[t]?arguments[t]:{};t%2?r(Object(n),!0).forEach((function(t){i(e,t,n[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(n)):r(Object(n)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(n,t))}))}return e}function l(e,t){if(null==e)return{};var n,a,o=function(e,t){if(null==e)return{};var n,a,o={},i=Object.keys(e);for(a=0;a<i.length;a++)n=i[a],t.indexOf(n)>=0||(o[n]=e[n]);return o}(e,t);if(Object.getOwnPropertySymbols){var i=Object.getOwnPropertySymbols(e);for(a=0;a<i.length;a++)n=i[a],t.indexOf(n)>=0||Object.prototype.propertyIsEnumerable.call(e,n)&&(o[n]=e[n])}return o}var c=o.a.createContext({}),p=function(e){var t=o.a.useContext(c),n=t;return e&&(n="function"==typeof e?e(t):s(s({},t),e)),n},d=function(e){var t=p(e.components);return o.a.createElement(c.Provider,{value:t},e.children)},b={inlineCode:"code",wrapper:function(e){var t=e.children;return o.a.createElement(o.a.Fragment,{},t)}},u=o.a.forwardRef((function(e,t){var n=e.components,a=e.mdxType,i=e.originalType,r=e.parentName,c=l(e,["components","mdxType","originalType","parentName"]),d=p(n),u=a,m=d["".concat(r,".").concat(u)]||d[u]||b[u]||i;return n?o.a.createElement(m,s(s({ref:t},c),{},{components:n})):o.a.createElement(m,s({ref:t},c))}));function m(e,t){var n=arguments,a=t&&t.mdxType;if("string"==typeof e||a){var i=n.length,r=new Array(i);r[0]=u;var s={};for(var l in t)hasOwnProperty.call(t,l)&&(s[l]=t[l]);s.originalType=e,s.mdxType="string"==typeof e?e:a,r[1]=s;for(var c=2;c<i;c++)r[c]=n[c];return o.a.createElement.apply(null,r)}return o.a.createElement.apply(null,n)}u.displayName="MDXCreateElement"}}]);