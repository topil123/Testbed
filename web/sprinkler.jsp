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
        <div id="SprinklerContainer">

        </div>

        <script type="text/javascript">
            $(document).ready(function () {
                $('#SprinklerContainer').jtable({
                    title: 'Sprinklers',
//                    paging: true,
//                    sorting: true,
                    openChildAsAccordion: true,
                    actions: {
                        listAction: 'sprinkler?action=list',
                        createAction: 'sprinkler?action=create',
                        updateAction: 'sprinkler?action=update',
                        deleteAction: 'sprinkler?action=delete'
                    },
                    fields: {
                        //CHILD TABLE DEFINITION FOR "sprinkler settings"
                        setting: {
                            title: '',
                            width: '5%',
                            sorting: false,
                            edit: false,
                            create: false,
                            display: function (sprinklerData) {
                                //Create an image that will be used to open child table
                                var $img = $('<img src="css/jtable/themes/metro/add.png" title="Edit Sprinkler Settings" />');
                                //Open child table when user clicks the image
                                $img.click(function () {
                                    $('#SprinklerContainer').jtable('openChildTable',
                                            $img.closest('tr'),
                                            {
                                                    title: sprinklerData.record.sprinklerid + ' - Settings',
                                                actions: {
                                                    listAction: 'sprinklerSetting?action=list&sprinklerid=' + sprinklerData.record.sprinklerid,
                                                    createAction: 'sprinklerSetting?action=create',
                                                    updateAction: 'sprinklerSetting?action=update&sprinklerid=' + sprinklerData.record.sprinklerid,
                                                    deleteAction: 'sprinklerSetting?action=delete&sprinklerid=' + sprinklerData.record.sprinklerid,
                                                },
                                                fields: {
                                                    sprinklerid: {
                                                        type: 'hidden',
                                                        defaultValue: sprinklerData.record.sprinklerid
                                                    },
                                                    settingid: {
                                                        key: true,
                                                        create: false,
                                                        edit: false,
                                                        list: false
                                                    },
                                                    dayofweek: {
                                                        title: 'Day of Week',
                                                        width: '20%',
                                                        options: {'2': 'Mon', '3': 'Tue', '4': 'Wed', '5': 'Thur', '6': 'Fri', 
                                                                  '7': 'Sat', '1': 'Sun'}
                                                    },
                                                    starttime: {
                                                        title: 'Start Time',
                                                        width: '30%',
                                                        
                                                    },
                                                    endtime: {
                                                        title: 'End Time',
                                                        width: '30%'
                                                    }
                                                    
                                                }
                                            }, function (data) { //opened handler
                                        data.childTable.jtable('load');
                                    });
                                });
                                //Return image to show on the person row
                                return $img;
                            }
                        },
                        sprinklerid: {
                            title: 'ID',
                            key: true,
                            create: true,
                            width: '20%'
                        },
                        description: {
                            title: 'Description',
                            width: '40%'
                        },
                        controlmode: {
                            title: 'Mode',
                            width: '20%'
                        },
                        status: {
                            title: 'Status',
                            width: '30%',
                        }
                    }
                });
                $('#SprinklerContainer').jtable('load');

            });
        </script>
    </body>
</html>
