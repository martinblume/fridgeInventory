<#-- @ftlvariable name="" type="info.martinblume.fridgeinventory.rfidregistration.views.RfidItemsView" -->
</html>
    <!DOCTYPE html>
    <html>
    <head>
        <meta charset="UTF-8">
        <title>Register a new RFID item</title>
    </head>

    <body>
    <div>
        <input type="text" id="id" value="id">
        <br>
        <input type="text" id="name" value="name">
        <br><br>
        <input id="submit" type="button" value="submit" onclick="submit()" />
        <script type="text/javascript">
    function createRequest() {
      var result = null;
      if (window.XMLHttpRequest) {
        // FireFox, Safari, etc.
        result = new XMLHttpRequest();
        result.overrideMimeType('application/json'); // Or anything else
      }
      else if (window.ActiveXObject) {
        // MSIE
      }
      else {
        // No known mechanism -- consider aborting the application
      }
      return result;
    }

    function submit(){
        var name = document.getElementById("name").value;
        var id = document.getElementById("id").value;
        var req = createRequest(); // defined above
        var data = {};
        data.id = id;
        data.name = name;
        // Create the callback:
        req.onreadystatechange = function() {
          if (req.readyState != 4) return; // Not there yet
          if (req.status != 200) {
            // Handle request failure here...
            return;
          }
          // Request successful, read the response
          var resp = req.responseText;
          alert(resp);
        }

        req.open("POST", "/api/rfidItems", true);
        req.setRequestHeader("Content-Type",
                             "application/json; charset=UTF-8");
        req.send(JSON.stringify(data));
    }
  </script>
    </div>
    <div>
        <h1>Items:</h1>
        <ul>
            <#list rfidItems as item>
                <li>${item.id}: ${item.name}
            </#list>
        </ul>
    </div>
    </body>

    </html>