<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Fit Layout</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" /><link href="../../../res/css/demo.css" rel="stylesheet" type="text/css" />

    
    <script src="../../../scripts/boot.js" type="text/javascript"></script>


</head>
<body >   
       <style type="text/css">
    html, body{
        margin:0;padding:0;border:0;width:100%;height:100%;overflow:hidden;
    }    
    </style>
    <div class="mini-toolbar" style="padding:2px;border-bottom:0;">
        <table style="width:100%;">
            <tr>
            <td style="width:100%;">
                <a class="mini-button" iconCls="icon-save" plain="true">保存</a>
                <a class="mini-button" iconCls="icon-close" plain="true">关闭</a>
                <span class="separator"></span>
                <a class="mini-button" iconCls="icon-reload" plain="true">刷新</a>
            </td>
            <td style="white-space:nowrap;"><label style="font-family:Verdana;">名称: </label>
                <input class="mini-textbox" />
                <a class="mini-button" iconCls="icon-search" plain="true" onclick="onSearch()">查询</a>
                </td>
            </tr>
        </table>
    </div>
    <!--撑满页面-->
    <div class="mini-fit" >
        
        <div id="datagrid1" class="mini-datagrid" style="width:100%;height:100%;" 
            url="../data/AjaxService.aspx?method=SearchEmployees"  idField="id"
            sizeList="[5,10,20,50]" pageSize="10"
        >
            <div property="columns">
                <div type="indexcolumn" ></div>
                <div field="loginname" width="120" headerAlign="center" allowSort="true">员工帐号</div>    
                <div field="name" width="120" headerAlign="center" allowSort="true">姓名</div>                            
                <div field="gender" width="100" renderer="onGenderRenderer" align="center" headerAlign="center">性别</div>
                <div field="salary" width="100" allowSort="true">薪资</div>                                    
                <div field="age" width="100" allowSort="true">年龄</div>
                <div field="createtime" width="100" headerAlign="center" dateFormat="yyyy-MM-dd" allowSort="true">创建日期</div>                
            </div>
        </div> 

    </div>
    
    <script type="text/javascript">
        mini.parse();
        var grid = mini.get("datagrid1");
        grid.load();

        function onSearch() {
            mini.open({
                url: bootPATH + "../demo/CommonLibs/SelectGridWindow.html"
            });
        }
    </script>

</body>
</html>