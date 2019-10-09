Enter your name:
<form action="/signin" method="POST">
        <input name="playerName" />
        <br/>
        <button type="submit">Sign Up</button>
        <div class="page">
                    <div class="body">

                    <!-- Provide a message to the user, if supplied. -->
                    <#include "message.ftl" />

                    <!-- TODO: future content on the Home:
                            to start games,
                            spectating active games,
                            or replay archived games
                    -->
                    </div>
                </div>
    </form>






