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
        <div id="ACContainer">

        </div>

        <script type="text/javascript">
            $(document).ready(function () {
                $('#ACContainer').jtable({
                    title: 'HVAC',
//                    paging: true,
//                    sorting: true,
                    openChildAsAccordion: true,
                    actions: {
                        listAction: 'ac?action=list',
                        createAction: 'ac?action=create',
                        updateAction: 'ac?action=update',
                        deleteAction: 'ac?action=delete'
                    },
                    fields: {
                        //CHILD TABLE DEFINITION FOR "ac settings"
                        setting: {
                            title: '',
                            width: '5%',
                            sorting: false,
                            edit: false,
                            create: false,
                            display: function (acData) {
                                //Create an image that will be used to open child table
                                var $img = $('<img src="css/jtable/themes/metro/add.png" title="Edit AC Settings" />');
                                //Open child table when user clicks the image
                                $img.click(function () {
                                    $('#ACContainer').jtable('openChildTable',
                                            $img.closest('tr'),
                                            {
                                                    title: acData.record.acid + ' - Settings',
                                                actions: {
                                                    listAction: 'acSetting?action=list&acid=' + acData.record.acid,
                                                    createAction: 'acSetting?action=create',
                                                    updateAction: 'acSetting?action=update&acid=' + acData.record.acid,
                                                    deleteAction: 'acSetting?action=delete'
                                                },
                                                fields: {
                                                    acid: {
                                                        type: 'hidden',
                                                        defaultValue: acData.record.acid
                                                    },
                                                    settingid: {
                                                        key: true,
                                                        create: false,
                                                        edit: false,
                                                        list: false
                                                    },
                                                    dayofweek: {
                                                        title: 'Day of Week',
                                                        width: '15%',
                                                        options: {'1': 'Mon', '2': 'Tue', '3': 'Wed', '4': 'Thur', '5': 'Fri', 
                                                                  '6': 'Sat', '7': 'Sun'}
                                                    },
                                                    starttime: {
                                                        title: 'Start Time',
                                                        width: '25%',
                                                        
                                                    },
                                                    endtime: {
                                                        title: 'End Time',
                                                        width: '25%'
                                                    },
                                                    temperature: {
                                                        title: 'Temperature',
                                                        width: '15%'
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
                        acid: {
                            title: 'ID',
                            key: true,
                            create: true,
                            width: '10%'
                        },
                        description: {
                            title: 'Description',
                            width: '30%'
                        },
                        controlmode: {
                            title: 'Mode',
                            width: '15%'
                        },
                        status: {
                            title: 'Status',
                            width: '15%',
                        },
                        defaulttemperature: {
                            title: 'Default',
                            width: '15%'
                        },
                        currtemperature: {
                            title: 'Current',
                            width: '15%',
                            create: false,
                            edit: false
                        }
                    }
                });
                $('#ACContainer').jtable('load');

            });
        </script>
    </body>
</html>
