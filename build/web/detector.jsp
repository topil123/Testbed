<%-- 
    Document   : sprinkler
    Created on : Sep 30, 2014, 3:42:57 PM
    Author     : Zheng
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Include one of jTable styles. -->
        <link href="css/jtable/themes/lightcolor/blue/jtable.min.css" rel="stylesheet" type="text/css" />

        <!-- Include script file. -->

        <script src="js/jquery-1.11.1.min.js"></script>
        <script src="js/jquery-ui-1.11.1.js"></script>
        <script src="js/jquery.jtable-2.4.0.min.js" type="text/javascript"></script>
    </head>
    <body>
        <div id="DetectorContainer">
        </div>

        <script type="text/javascript">
            $(document).ready(function () {
                $('#DetectorContainer').jtable({
                    title: 'Detectors',
//                    paging: true,
//                    sorting: true,
//                    openChildAsAccordion: true,
                    actions: {
                        listAction: 'detector?action=list',
                        createAction: 'detector?action=create',
                        updateAction: 'detector?action=update',
                        deleteAction: 'detector?action=delete'
                    },
                    fields: {
                        
                        detectorid: {
                            title: 'ID',
                            key: true,
                            width: '20%',
                            create: true
                        },
                        description: {
                            title: 'Description',
                            width: '50%'
                        },
                        status: {
                            title: 'Status',
                            width: '30%',
                            options: {'On': 'On', 'Off': 'Off'}
                        }
                    }
                });
                $('#DetectorContainer').jtable('load');

            });
        </script>
    </body>
</html>
