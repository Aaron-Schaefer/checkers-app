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

    <#if currentUser??>
        </br>
        <#list players as player>
            <#if currentUser.name != player>
                <a href="/game">${player}</a></br></br>
            <#else>
                <#if numPlayers lt 2>
                    There are no other players available to play at this time.
                </#if>
            </#if>
        </#list>
    <#else>
        <p>There <#if numPlayers == 1> is<#else> are</#if> currently
        ${numPlayers} Player<#if numPlayers != 1>s</#if> signed in.
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
