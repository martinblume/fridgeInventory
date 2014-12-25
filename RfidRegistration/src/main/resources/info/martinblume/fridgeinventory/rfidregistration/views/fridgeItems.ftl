<#-- @ftlvariable name="" type="info.martinblume.fridgeinventory.rfidregistration.views.FridgeItemsView" -->
    </html>
    <!DOCTYPE html>
    <html>
    <head>
        <meta charset="UTF-8">
        <title>Fridge Items</title>
    </head>

    <body>
    <div>
        <h1>Items:</h1>
        <ul>
            <#list fridgeItems as item>
                <#if item.isInFridge>
                    <li>${item.id}: ${item.name
                </#if>
            </#list>
        </ul>
    </div>
    </body>

    </html>