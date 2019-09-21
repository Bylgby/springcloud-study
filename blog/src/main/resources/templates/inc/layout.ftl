<#-- Layout -->
<#macro layout title>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!--[if IE]>
    <meta http-equiv='X-UA-Compatible' content='IE=edge,chrome=1'/>
    <![endif]-->

    <title>${title?default('Martin-yuyy')}</title>

    <link rel="stylesheet" href="${base}/res/layui/css/layui.css">
    <link rel="stylesheet" href="${base}/res/css/global.css">

    <script src="${base}/layui/layui/layui.js"></script>

</head>
<body>

<#include "/inc/header.ftl" />

<#nested>

<#include "/inc/footer.ftl" />
</body>
</html>
</#macro>