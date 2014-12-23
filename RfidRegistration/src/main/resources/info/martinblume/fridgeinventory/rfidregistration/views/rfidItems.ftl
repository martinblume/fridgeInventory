<#-- @ftlvariable name="" type="info.martinblume.fridgeinventory.rfidregistration.views.RfidItemsView" -->
<html>
<head>
    <title>Welcome!</title>
</head>
<body>
    <h1>Items:</h1>
    <ul>
        <#list rfidItems as item>
            <li>${item.id}: ${item.name}
        </#list>
    </ul>
</body>
</html>