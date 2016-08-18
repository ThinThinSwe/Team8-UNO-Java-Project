$(function () {

    var currentGameId;
    var currentGameName;
    var playerId;

    var connection = null;
   

    var displayConnection = function (msg) {
        $("#socketconnect").prepend(
                $("<div>").text(msg)
                );
    }



    //  -----------  Available Game List   --------------------
    var promise = $.getJSON("http://localhost:8080/UNO/api/game/gamelist");
    promise.done(function (result) { // 200

        for (var i = 0; i < result.length; i++) {

            var game = result[i];
            var colum = (game.gname).toString() + "  (" + (game.gid).toString() + ") " + (game.status).toString();
            var liCol = $("<li>").text(colum.toString())
                    .attr("data-gameId", game.gid)
                    .attr("data-gameName", game.gname);

            $("#all-games").append(liCol);
        }


    });

    //  -----------  When  Player click on a game  --------------------

    $("#all-games").on("singletap", "li", function () {

        //var gameNo = $(this).find("h2").text();
        var gid = $(this).attr("data-gameId");
        currentGameId = gid;

        var gn = $(this).attr("data-gameName");
        currentGameName = gn;
        $("#ggName").text(gn.toString());

        var game = gid + gn;
        $("#gID").text(gid.toString());
        console.info(">>>>> Player" + currentGameId + currentGameName)
        $.UIGoToArticle("#createplayer");
    });

    //  -----------  Add New Player   --------------------

    $("#join").on("singletap", function () {
        var gid = currentGameId;
        var pname = $("#pname").val();
        console.info("> JOINING game: %", currentGameId);
        $.post("http://localhost:8080/UNO/api/game/addplayer/" + gid + "/" + pname)
                .done(function (result) {
                    //console.info("> result from addPlayer: %s", JSON.stringify(result));
                    playerId = result.pid;
                    console.info("Player ID " + playerId);
                    connection = new WebSocket("ws://localhost:8080/UNO/table/" + currentGameId);
                    connection.onopen = function () {
                        displayConnection("websocket is connected" + gid + " " + playerId + " " + pname);
                        var sendmessage = {
                            gid: currentGameId,
                            pname: pname,
                            pid: playerId
                        }
                        connection.send(JSON.stringify(sendmessage));

                    };

                    connection.onclose = function () {
                        displayConnection("websocket is closed");
                    }

                    connection.onmessage = function (msg) {
                        console.info("incoming: ", msg.data);
                        var newMessage = JSON.parse(msg.data);
                        displayConnection("Player Name " +newMessage.pname);
                    }

                });

        $("#GAMETITLE").text("Waiting for \" " + currentGameName + " \" to start");

        $.UIGoToArticle("#status");

    });

    var myTimer = setInterval(myCard, 1000);

    function myCard() {
        var d = new Date();
        var status = "";
        connection.onmessage = function (msg) {
            console.info("incoming: ", msg.data);
            var newMsg = JSON.parse(msg.data);
            status = newMsg.status;
            if (status == "start") {
                displayCardView();
            }

            var serverflag = newMsg.serverflag;

            if (serverflag && newMsg.pid === playerId)
            {
                $('#discardImage').append('<img src="images/' + newMsg.image + '.png" alt="' + newMsg.image + '" title="' + newMsg.cid + '" />');
            }
        }
    }

    function displayCardView() {
        //alert("inside card view");
        console.info("@>> start play: %s", playerId + currentGameId);
        $.get("http://localhost:8080/UNO/api/game/viewdealcard/" + currentGameId + "/" + playerId)
                .done(function (result) {
                    console.info(">>>>View Card Loop IN " + result.length);
                    for (var i = 0; i < result.length; i++)
                    {
                        console.info(">>>>Inside For");
                        $('#handCardImg').append('<img src="images/' + result[i].image + '.png" alt="' + result[i].image + '" title="' + result[i].cid + '" class="absolute' + i + '" />');
                    }
                    console.info(">>>>Outside For");
                    $.UIGoToArticle("#handincard");
                });
    }
    ;

    //Discard Card to Deck When user Click Card
    $("#handCardImg").on("singletap", "img", function () {
        var currentDiscardCardid = $(this).attr("title");
        var currentDiscardImg = $(this).attr("alt");

        //Sending Msg to WebSocket
        var message = {
            gid: currentGameId,
            cid: currentDiscardCardid,
            image: currentDiscardImg,
            flag: true
        }
        connection.send(JSON.stringify(message));


        //POST METHOD to delect the selected card from player's list in hand card.
        //Write POST Method <<playerdiscardtodiscardpile>>
        console.info(">>>>>> Discard Card In Hand " + currentGameId + " " + currentDiscardCardid);
        $.post("http://localhost:8080/UNO/api/game/playerdiscardtodiscardpile/" + currentGameId + "/" + playerId + "/" + currentDiscardCardid);

        //DELETE Selected Card From Player View

    });

});

