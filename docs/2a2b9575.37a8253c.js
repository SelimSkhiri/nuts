(window.webpackJsonp=window.webpackJsonp||[]).push([[12],{101:function(e,n,t){"use strict";t.d(n,"a",(function(){return u})),t.d(n,"b",(function(){return b}));var a=t(0),o=t.n(a);function i(e,n,t){return n in e?Object.defineProperty(e,n,{value:t,enumerable:!0,configurable:!0,writable:!0}):e[n]=t,e}function r(e,n){var t=Object.keys(e);if(Object.getOwnPropertySymbols){var a=Object.getOwnPropertySymbols(e);n&&(a=a.filter((function(n){return Object.getOwnPropertyDescriptor(e,n).enumerable}))),t.push.apply(t,a)}return t}function p(e){for(var n=1;n<arguments.length;n++){var t=null!=arguments[n]?arguments[n]:{};n%2?r(Object(t),!0).forEach((function(n){i(e,n,t[n])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(t)):r(Object(t)).forEach((function(n){Object.defineProperty(e,n,Object.getOwnPropertyDescriptor(t,n))}))}return e}function c(e,n){if(null==e)return{};var t,a,o=function(e,n){if(null==e)return{};var t,a,o={},i=Object.keys(e);for(a=0;a<i.length;a++)t=i[a],n.indexOf(t)>=0||(o[t]=e[t]);return o}(e,n);if(Object.getOwnPropertySymbols){var i=Object.getOwnPropertySymbols(e);for(a=0;a<i.length;a++)t=i[a],n.indexOf(t)>=0||Object.prototype.propertyIsEnumerable.call(e,t)&&(o[t]=e[t])}return o}var l=o.a.createContext({}),s=function(e){var n=o.a.useContext(l),t=n;return e&&(t="function"==typeof e?e(n):p(p({},n),e)),t},u=function(e){var n=s(e.components);return o.a.createElement(l.Provider,{value:n},e.children)},d={inlineCode:"code",wrapper:function(e){var n=e.children;return o.a.createElement(o.a.Fragment,{},n)}},m=o.a.forwardRef((function(e,n){var t=e.components,a=e.mdxType,i=e.originalType,r=e.parentName,l=c(e,["components","mdxType","originalType","parentName"]),u=s(t),m=a,b=u["".concat(r,".").concat(m)]||u[m]||d[m]||i;return t?o.a.createElement(b,p(p({ref:n},l),{},{components:t})):o.a.createElement(b,p({ref:n},l))}));function b(e,n){var t=arguments,a=n&&n.mdxType;if("string"==typeof e||a){var i=t.length,r=new Array(i);r[0]=m;var p={};for(var c in n)hasOwnProperty.call(n,c)&&(p[c]=n[c]);p.originalType=e,p.mdxType="string"==typeof e?e:a,r[1]=p;for(var l=2;l<i;l++)r[l]=t[l];return o.a.createElement.apply(null,r)}return o.a.createElement.apply(null,t)}m.displayName="MDXCreateElement"},66:function(e,n,t){"use strict";t.r(n),t.d(n,"frontMatter",(function(){return r})),t.d(n,"metadata",(function(){return p})),t.d(n,"rightToc",(function(){return c})),t.d(n,"default",(function(){return s}));var a=t(2),o=t(6),i=(t(0),t(101)),r={id:"nutsApp",title:"Nuts Applications",sidebar_label:"Nuts Applications"},p={unversionedId:"dev/nutsApp",id:"dev/nutsApp",isDocsHomePage:!1,title:"Nuts Applications",description:"Making you first nuts application",source:"@site/docs/dev/nuts-app.md",permalink:"/nuts/docs/dev/nutsApp",editUrl:"https://github.com/facebook/docusaurus/edit/master/website/docs/dev/nuts-app.md",sidebar_label:"Nuts Applications",sidebar:"someSidebar",previous:{title:"Building",permalink:"/nuts/docs/dev/building"},next:{title:"Nuts Path",permalink:"/nuts/docs/dev/nutsPath"}},c=[],l={rightToc:c};function s(e){var n=e.components,t=Object(o.a)(e,["components"]);return Object(i.b)("wrapper",Object(a.a)({},l,t,{components:n,mdxType:"MDXLayout"}),Object(i.b)("h1",{id:"making-you-first-nuts-application"},"Making you first nuts application"),Object(i.b)("p",null,"Lets take, step by step, an example of an application that you will run using ",Object(i.b)("inlineCode",{parentName:"p"},"nuts")," package manager"),Object(i.b)("ol",null,Object(i.b)("li",{parentName:"ol"},"create a java maven project First you will create the project using you favourite IDE or using mvn")),Object(i.b)("pre",null,Object(i.b)("code",Object(a.a)({parentName:"pre"},{}),"mvn archetype:generate -DgroupId=com.mycompany.app -DartifactId=my-app -DarchetypeArtifactId=maven-archetype-simple -DarchetypeVersion=1.4 -DinteractiveMode=false\n")),Object(i.b)("p",null,"you will have a fully generated java project"),Object(i.b)("pre",null,Object(i.b)("code",Object(a.a)({parentName:"pre"},{className:"language-aidl"}),"vpc@linux-rogue:~/ttt> tree\n.\n\u2514\u2500\u2500 my-app\n    \u251c\u2500\u2500 pom.xml\n    \u2514\u2500\u2500 src\n        \u251c\u2500\u2500 main\n        \u2502   \u2514\u2500\u2500 java\n        \u2502       \u2514\u2500\u2500 com\n        \u2502           \u2514\u2500\u2500 mycompany\n        \u2502               \u2514\u2500\u2500 app\n        \u2502                   \u2514\u2500\u2500 App.java\n        \u2514\u2500\u2500 test\n            \u2514\u2500\u2500 java\n                \u2514\u2500\u2500 com\n                    \u2514\u2500\u2500 mycompany\n                        \u2514\u2500\u2500 app\n                            \u2514\u2500\u2500 AppTest.java\n\n")),Object(i.b)("p",null,"Now we will add some dependency to the project. Lets add ",Object(i.b)("inlineCode",{parentName:"p"},"jexcelapi:jxl#2.4.2")," and update pom.xml consequently."),Object(i.b)("pre",null,Object(i.b)("code",Object(a.a)({parentName:"pre"},{className:"language-xml"}),'<?xml version="1.0" encoding="UTF-8"?>\n<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"\n         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">\n    <modelVersion>4.0.0</modelVersion>\n    <groupId>com.mycompany.app</groupId>\n    <artifactId>my-app</artifactId>\n    <version>1.0-SNAPSHOT</version>\n    <packaging>jar</packaging>\n    <dependencies>\n        <dependency>\n            <groupId>jexcelapi</groupId>\n            <artifactId>jxl</artifactId>\n            <version>2.4.2</version>\n        </dependency>\n    </dependencies>\n    <properties>\n        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>\n        <maven.compiler.source>1.8</maven.compiler.source>\n        <maven.compiler.target>1.8</maven.compiler.target>\n    </properties>\n</project> \n')),Object(i.b)("p",null,"Now Update the App.java file"),Object(i.b)("pre",null,Object(i.b)("code",Object(a.a)({parentName:"pre"},{className:"language-java"}),'package com.mycompany.app;\n\nimport java.io.File;\n\nimport jxl.Workbook;\nimport jxl.write.WritableWorkbook;\n\npublic class App {\n\n    public static void main(String[] args) {\n        try {\n            WritableWorkbook w = Workbook.createWorkbook(new File("a.xls"));\n            System.out.println("Workbook just created");\n        } catch (Exception ex) {\n            ex.printStackTrace();\n        }\n    }\n}\n\n')),Object(i.b)("p",null,"finally compile the app:"),Object(i.b)("pre",null,Object(i.b)("code",Object(a.a)({parentName:"pre"},{className:"language-bash"}),"mvn clean install\n")),Object(i.b)("p",null,"Of course, you won't be able to run the application yet. Would you? For this app to work there are several ways, all of\nthem are complicated and require modifying the pom and even modifying the output jar. You can for instance generate and\noutput lib directory and update the META-INF file using maven-dependency-plugin.\n(see ",Object(i.b)("a",Object(a.a)({parentName:"p"},{href:"https://maven.apache.org/plugins/maven-shade-plugin"}),"https://maven.apache.org/plugins/maven-shade-plugin")," ; ",Object(i.b)("a",Object(a.a)({parentName:"p"},{href:"https://www.baeldung.com/executable-jar-with-maven"}),"https://www.baeldung.com/executable-jar-with-maven"),"). You\ncould also use ",Object(i.b)("inlineCode",{parentName:"p"},"maven-assembly-plugin")," to include the dependencies into the jar itself ('what the fat' jar!).\nAnother alternative is to use an uglier solution with ",Object(i.b)("inlineCode",{parentName:"p"},"maven-shade-plugin")," and blend libraries into the main jar. In\nall cases you need as well to configure maven-jar-plugin to specify the main class file."),Object(i.b)("p",null,"I am not exposing all solutions here. You can read this article for more\ndetails (",Object(i.b)("a",Object(a.a)({parentName:"p"},{href:"https://www.baeldung.com/executable-jar-with-maven"}),"https://www.baeldung.com/executable-jar-with-maven"),") but trust me, they all stink."),Object(i.b)("p",null,"Instead of that we will use nuts. In that case, actually you are already done, the app is already okkay! you do not need\nto specify the main class neither are your required to bundle jxl and its dependencies. you only need to run the app.\nThat's it."),Object(i.b)("pre",null,Object(i.b)("code",Object(a.a)({parentName:"pre"},{className:"language-bash"}),"nuts -y com.mycompany.app:my-app\n")),Object(i.b)("p",null,"this will install the application and run it on the fly. Dependencies will be detected, resolved and downloaded. The\napplication is installed from your local maven repository. It needs to be deployed to a public repository for it to be\npublicly accessible, however."),Object(i.b)("p",null,"You can also choose not to install the app and bundle it as a jar. No need for a public repository in that case:"),Object(i.b)("pre",null,Object(i.b)("code",Object(a.a)({parentName:"pre"},{className:"language-bash"}),"nuts -y com my-app-1.0.0-SNAPSHOT.jar\n")),Object(i.b)("p",null,"As we can see, nuts provides the simplest and thee most elegant way to deploy your application."),Object(i.b)("p",null,"One question though. what happens if we define multiple main methods (in multiple public classes). It is handled as well\nby ",Object(i.b)("inlineCode",{parentName:"p"},"nuts")," seamlessly. It just asks, at runtime, for the appropriate class to run."),Object(i.b)("h1",{id:"using-nuts-application-framework"},"Using Nuts Application Framework"),Object(i.b)("p",null,"Using ",Object(i.b)("inlineCode",{parentName:"p"},"nuts")," if transparent as we have seen so far. It is transparent both at build time and run time.\nHowever, ",Object(i.b)("inlineCode",{parentName:"p"},"nuts")," can provide our application a set of unique helpful features, such as install adn uninstall hooks,\ncomprehensive command line support and so on."),Object(i.b)("p",null,"To create your first ",Object(i.b)("inlineCode",{parentName:"p"},"NAF")," application, you will need to add nuts as a dependency and change your main class as\nfollows:"),Object(i.b)("pre",null,Object(i.b)("code",Object(a.a)({parentName:"pre"},{className:"language-xml"}),'<?xml version="1.0" encoding="UTF-8"?>\n<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"\n         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">\n    <modelVersion>4.0.0</modelVersion>\n    <groupId>com.mycompany.app</groupId>\n    <artifactId>my-app</artifactId>\n    <version>1.0-SNAPSHOT</version>\n    <packaging>jar</packaging>\n    <dependencies>\n        <dependency>\n            <groupId>net.thevpc.nuts</groupId>\n            <artifactId>nuts</artifactId>\n            <version>0.8.3</version>\n        </dependency>\n        <dependency>\n            <groupId>jexcelapi</groupId>\n            <artifactId>jxl</artifactId>\n            <version>2.4.2</version>\n        </dependency>\n    </dependencies>\n    <properties>\n        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>\n        <maven.compiler.source>1.8</maven.compiler.source>\n        <maven.compiler.target>1.8</maven.compiler.target>\n        <nuts.application>true</nuts.application>\n    </properties>\n</project> \n')),Object(i.b)("p",null,"Please take note that we have added a property 'nuts.application=true'.\nActually this is no mandatory, but this will help ",Object(i.b)("inlineCode",{parentName:"p"},"nuts")," package manager detect that this application uses NAF before\ndownloading it jar (the information will be available in the ",Object(i.b)("inlineCode",{parentName:"p"},"pom.xml")," descriptor on the remote repository)."),Object(i.b)("p",null,'Then we will add some cool features to our application. We write a dummy message whenever the application is installed, uninstalled or updated.\nWe will also add support to "--file=',"[path]",'" argument to specify the workbook path.'),Object(i.b)("pre",null,Object(i.b)("code",Object(a.a)({parentName:"pre"},{className:"language-java"}),'package com.mycompany.app;\n\nimport java.io.File;\n\nimport jxl.Workbook;\nimport jxl.write.WritableWorkbook;\n\npublic class App implements NutsApplication {\n\n    public static void main(String[] args) {\n        // just create an instance and call runAndExit in the main method\n        new App().runAndExit(args);\n    }\n\n    @Override\n    public void run(NutsApplicationContext applicationContext) {\n        NutsSession s = applicationContext.getSession();\n        NutsCommandLine cmd = applicationContext.getCommandLine();\n        File file = new File("a.xls");\n        while (cmd.hasNext()) {\n            switch (cmd.getKeyString()) {\n                case "--file": {\n                    NutsArgument a = cmd.nextString();\n                    file = new File(a.getStringValue());\n                    break;\n                }\n                default: {\n                    cmd.unexpectedArgument();\n                }\n            }\n        }\n        try {\n            WritableWorkbook w = Workbook.createWorkbook(file);\n            s.out().printf("Workbook just created at %s%n", file);\n        } catch (Exception ex) {\n            ex.printStackTrace(s.err());\n        }\n    }\n\n    @Override\n    public void onInstallApplication(NutsApplicationContext applicationContext) {\n        NutsSession s = applicationContext.getSession();\n        s.out().printf("we are installing My Application : %s%n", applicationContext.getId());\n    }\n\n    @Override\n    public void onUninstallApplication(NutsApplicationContext applicationContext) {\n        NutsSession s = applicationContext.getSession();\n        s.out().printf("we are uninstalling My Application : %s%n", applicationContext.getId());\n    }\n\n    @Override\n    public void onUpdateApplication(NutsApplicationContext applicationContext) {\n        NutsSession s = applicationContext.getSession();\n        s.out().printf("we are updating My Application : %s%n", applicationContext.getId());\n    }\n}\n\n')),Object(i.b)("p",null,"now we can install or uninstall  the application and see the expected message."),Object(i.b)("pre",null,Object(i.b)("code",Object(a.a)({parentName:"pre"},{className:"language-bash"}),"nuts -y install com.mycompany.app:my-app\nnuts -y uninstall com.mycompany.app:my-app\n")))}s.isMDXComponent=!0}}]);