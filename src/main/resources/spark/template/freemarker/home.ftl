<!DOCTYPE html>

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
  <meta http-equiv="refresh" content="10">
  <title>Web Checkers | ${title}</title>
  <link rel="stylesheet" type="text/css" href="/css/style.css">
</head>

<body>
<div class="page">

  <h1>Web Checkers | ${title}</h1>

  <!-- Provide a navigation bar -->
  <#include "nav-bar.ftl" />

  <div class="body">

    <!-- Provide a message to the user, if supplied. -->
    <#include "message.ftl" />

    <h2>Players Online</h2>

    <#if currentUser?? && !mode??>
        Select a mode to play<br>
        <form action="/" method="GET">
         <input type="radio" name="mode" value="PLAY">PLAY<br>
         <input type="radio" name="mode" value="SPECTATOR">SPECTATOR<br>
         <input type="radio" name="mode" value="REPLAY">REPLAY<br>
         <input type="radio" name="mode" value="AI">AI<br>
         <button type='submit'>SELECT MODE</button>
    </#if>

    <#if currentUser?? && mode??>
        <#if mode == "PLAY" || mode = "SPECTATOR">
            </br>
          <form action="/game" method="GET">
            <#if numPlayers lt 2>
                There are no<#if mode == "PLAY"> other players available to play
                <#else> players to spectate</#if> at this time.
            <#else>
              <#list players as player>
                <#if currentUser.name != player.name>
                    <input type="radio" name="playerName" value=${player.name}>${player.name}<br>
                </#if>
              </#list>
            </#if>
              <#if numPlayers gt 1>
                <button type='submit'><#if mode == "PLAY">PLAY GAME<#else>SPECTATE GAME</#if></button>
              </#if>
          </form>
        <#elseif mode == "REPLAY">
            </br>
            <form action="/replay/game" method="GET">
                <#if numGames == 0>
                    There are no finished games to replay at this time.
                <#else>
                    <#list games as game>
                        <input type="radio" name="game" value=game>
                        Red: ${game.redPlayer.name}  White: ${game.whitePlayer.name}</br>
                    </#list>
                    <button type='submit'>REPLAY GAME</button>
                </#if>
             </form>
        </#if>
   <#elseif !currentUser??>
       <p>There <#if numPlayers == 1> is<#else> are</#if> currently
       ${numPlayers} User<#if numPlayers != 1>s</#if> signed in.
    </#if>




    <!-- TODO: future content on the Home:
            to start games,
            spectating active games,
            or replay archived games
    -->

  </div>

</div>
</body>

</html>
