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


    <#if currentUser?? && !mode??>
        <h2>Mode Options</h2>
        Select a mode to play<br>
        <form action="/" method="GET">
         <input type="radio" name="mode" value="PLAY">PLAY: Play against other Players
            (${numInPlay} active Player<#if numInPlay != 1>s</#if>)<br>
         <input type="radio" name="mode" value="SPECTATOR">SPECTATOR: Spectate an active Player<br>
         <input type="radio" name="mode" value="REPLAY">REPLAY: Replay finished games<br>
         <input type="radio" name="mode" value="AI">AI: Play against an AI<br>
         <button type='submit'>SELECT MODE</button>
        </form>
    </#if>

    <#if currentUser?? && mode??>
        <#if mode == "PLAY" || mode = "SPECTATOR">
            <#if mode == "PLAY"><h2>Players Online</h2>
            <#else> <h2>Players To Spectate</h2></#if><br>
          <form <#if mode == "PLAY">action="/game"<#else>action="/spectator/game"</#if> method="GET">
            <#if numPlayers lt 2>
                There are no<#if mode == "PLAY"> other players available to play
                <#else> players to spectate</#if> at this time.
            <#else>
              <#if mode == "PLAY"> Select an opponent:
              <#else> Select a Player to spectate: </#if><br>
              <#list players as player>
                <#if currentUser.name != player.name>
                    <input type="radio" name="playerName" checked value=${player.name}>${player.name} <br>
                </#if>
              </#list>
            </#if>
              <#if numPlayers gt 1>
                <button type='submit'><#if mode == "PLAY">PLAY GAME<#else>SPECTATE GAME</#if></button>
              </#if>
          </form>
        <#elseif mode == "REPLAY">
            <h2>Games To Replay</h2><br>
            <form action="/replay/game" method="GET">
                <#if numGames == 0>
                    There are no finished games to replay at this time.
                <#else>
                    Select a game to Replay:<br>
                    <#list games as game>
                        <input type="radio" name="game" value=${game.gameID}>
                        Red: ${game.redPlayer.name}  White: ${game.whitePlayer.name}  Game: ${game.gameID}<br>
                    </#list>
                    <button type='submit'>REPLAY GAME</button>
                </#if>
             </form>
        </#if>
   <#elseif !currentUser??>
      <h2>Users Online</h2>
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
