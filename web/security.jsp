<%-- 
    Document   : security
    Created on : Oct 2, 2014, 10:43:20 PM
    Author     : Zheng
--%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset=UTF-8">
        <script src="js/jquery-1.11.1.min.js"></script>

    </head>
    <body>
        <button id="monitor" onclick="securityEvent()">Start Monitor</button>
        <button onclick="stop()">Stop Monitor</button>
        <button onclick="clearText()">Clear</button> </br>
        <textarea id ="events" readonly="readonly" rows="20" cols="70"></textarea>


        <script src="js/noty/packaged/jquery.noty.packaged.js"></script>
        <script src="js/noty/layouts/topCenter.js"></script>
        <script src="js/noty/themes/default.js"></script>
        <script type="text/javascript">
            var eventSource = null;
            function securityEvent() {
                eventSource = new EventSource('SecurityEvent');
                eventSource.addEventListener('Security', handlePoliceEvent, false);
                eventSource.addEventListener('Fire', handleFireEvent, false);
                monitor.disabled = true;
            }
            ;
            function handlePoliceEvent(event) {
                events.value += 'Got Security event: \n';
                events.value += event.data + '\n\n';
                noty({
                    text: event.data,
                    layout: 'topCenter',
                    buttons: [
                        {addClass: 'btn btn-primary', text: 'Notify Police Office', onClick: function ($noty) {
                                $noty.close();                               
                            }
                        },
                        {addClass: 'btn btn-danger', text: 'Cancel', onClick: function ($noty) {
                                $noty.close();                               
                            }
                        }
                    ]
                });
            }
            ;
            function handleFireEvent(event) {
                events.value += 'Got Fire/CO/GAS event: \n';
                events.value += event.data + '\n\n';
                noty({
                    text: event.data,
                    layout: 'topCenter',
                    buttons: [
                        {addClass: 'btn btn-primary', text: 'Notify Fire Office', onClick: function ($noty) {
                                $noty.close();                               
                            }
                        },
                        {addClass: 'btn btn-danger', text: 'Cancel', onClick: function ($noty) {
                                $noty.close();                               
                            }
                        }
                    ]
                });
            }
            ;
            function stop() {
                eventSource.close();
                monitor.disabled = false;
            }
            ;
            function clearText() {
                events.value = '';
            }
            ;
        </script>

    </body>


</html>
