<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>数据源管理</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.5 -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/plugins/bootstrap/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/plugins/font-awesome/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/plugins/ionicons/ionicons.min.css">
    <!-- DataTables -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/plugins/datatables/dataTables.bootstrap.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/plugins/AdminLTE/AdminLTE.min.css">
    <!-- AdminLTE Skins. Choose a skin from the css/skins
         folder instead of downloading all of them to reduce the load. -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/plugins/AdminLTE/skins/_all-skins.min.css">
	<!-- date picker -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/plugins/datepicker/bootstrap-datepicker3.min.css">
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <body class="hold-transition skin-blue sidebar-mini">
    <div class="wrapper">

	  <jsp:include page="/jsp/support/common/header.jsp"/>
	  <jsp:include page="/jsp/support/common/menu.jsp"/>

      <!-- Content Wrapper. Contains page content -->
      <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
          <h1>
                                         新建数据源
          </h1>
          <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i>首页</a></li>
            <li><a href="#">代码生成</a></li>
            <li class="active">编辑数据源</li>
          </ol>
        </section>

        <!-- Main content -->
        <section class="content">
          <div class="row">
            <div class="col-xs-12">
              <div class="box">
                <div class="box-header">
                  <h3 class="box-title">数据源信息</h3>
                </div><!-- /.box-header -->
                <div class="box box-info">
                	<div class="box-body" id="msg_body"></div>
					<div class="box-header with-border">
						<s:form modelAttribute="dsModel" method="post" action="./saveDS" class="form-horizontal" id="ds_form" >
						<div class="form-group">
							<label for="ds_name" class="col-sm-4 control-label">数据源名称</label>
							<div class="col-sm-6">
							<s:input type="text" path="ds_name" class="form-control"/>
							</div>
						</div>
						<div class="form-group">
                      		<label for="ds_desc" class="col-sm-4 control-label">数据源描述</label>
                      		<div class="col-sm-6">
                      			<s:textarea class="form-control" rows="3" path="ds_desc"></s:textarea>
                      		</div>
                    	</div>
                    	<div class="form-group">
							<label for="ds_type" class="col-sm-4 control-label">数据源类型</label>
							<div class="col-sm-6">
							<s:select path="ds_type" id="ds_type" class="form-control">
							</s:select>
							</div>
						</div>
						<div class="form-group">
							<label for="ds_url" class="col-sm-4 control-label">数据源URL</label>
							<div class="col-sm-6">
							<s:input type="text" path="ds_url" class="form-control"/>
							</div>
						</div>
						<div class="form-group">
							<label for="ds_user" class="col-sm-4 control-label">用户名</label>
							<div class="col-sm-6">
							<s:input type="text" path="ds_user" class="form-control"/>
							</div>
						</div>
						<div class="form-group">
							<label for="ds_password" class="col-sm-4 control-label">密码</label>
							<div class="col-sm-6">
							<s:input type="text" path="ds_password" class="form-control"/>
							</div>
						</div>
						
						<div class="box-footer">
                    		<button type="submit" class="btn btn-default pull-right" id="cancel">取消</button>
                    		<button type="submit" class="btn btn-info pull-right" id="submit">保存</button>
                  		</div><!-- /.box-footer -->
						</s:form>
					</div>
                </div><!-- /.box-body -->
              </div><!-- /.box -->
            </div><!-- /.col -->
          </div><!-- /.row -->
        </section><!-- /.content -->
      </div><!-- /.content-wrapper -->
	  <jsp:include page="/jsp/support/common/footer.jsp"/>
	  <jsp:include page="/jsp/support/common/slideBar.jsp"/>

    </div><!-- ./wrapper -->
    <!-- mustache -->
    <script src="<%=request.getContextPath()%>/js/plugins/mustache/mustache.min.js"></script>
    <!-- jQuery 2.1.4 -->
    <script src="<%=request.getContextPath()%>/js/plugins/jQuery/jQuery-2.1.4.min.js"></script>
    <!-- Bootstrap 3.3.5 -->
    <script src="<%=request.getContextPath()%>/js/plugins/bootstrap/bootstrap.min.js"></script>
    <!-- DataTables -->
    <script src="<%=request.getContextPath()%>/js/plugins/datatables/jquery.dataTables.min.js"></script>
    <script src="<%=request.getContextPath()%>/js/plugins/datatables/dataTables.bootstrap.min.js"></script>
    <!-- SlimScroll -->
    <script src="<%=request.getContextPath()%>/js/plugins/slimScroll/jquery.slimscroll.min.js"></script>
    <!-- FastClick -->
    <script src="<%=request.getContextPath()%>/js/plugins/fastclick/fastclick.min.js"></script>
    <!-- AdminLTE App -->
    <script src="<%=request.getContextPath()%>/js/plugins/AdminLTE/app.min.js"></script>
    <!-- AdminLTE for demo purposes -->
    <script src="<%=request.getContextPath()%>/js/plugins/AdminLTE/demo.js"></script>
    <!-- InputMask -->
    <script src="<%=request.getContextPath()%>/js/plugins/input-mask/jquery.inputmask.js"></script>
    <script src="<%=request.getContextPath()%>/js/plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
    <script src="<%=request.getContextPath()%>/js/plugins/input-mask/jquery.inputmask.extensions.js"></script>
    <script src="<%=request.getContextPath()%>/js/plugins/datepicker/bootstrap-datepicker.min.js"></script>
    <script src="<%=request.getContextPath()%>/js/common/commonUtils.js"></script>
    <script src="<%=request.getContextPath()%>/js/common/commonConstant.js"></script>
    <!-- page script -->
    <script>
      $(function () {
          $('#sumbit').click(function(){
        	  $("ds_form").submit();
          });
      });
      $(document).ready(function() {

    	  var res = '${result}';
    	  if(res){
    		  res = $.parseJSON('${result}');
    		  if(res.status==SUCCESS_ST){
        		  console.info(res);
        		  var msg_tpl = $("#success_msg_tpl").html();  
        		  var msg_viewer = $("#msg_body");
        		  renderView(msg_tpl, res, msg_viewer);
    		  }else{
        		  console.info(res);
        		  var msg_tpl = $("#error_msg_tpl").html();  
        		  var msg_viewer = $("#msg_body");
        		  renderView(msg_tpl, res, msg_viewer);
    		  }

    	  }
    	  var ds_type = '${typeList}';
    	  if(ds_type){
        	  // select components
        	  ds_type = $.parseJSON('${typeList}');
        	  var tpl = $("#ds_type_tpl").html();
              var viewer = $("#ds_type");
              renderView(tpl, ds_type, viewer);
    	  }

      });
    </script>
    <!-- 显示模板 -->
	<script type="text/x-mustache" id="ds_type_tpl">
     <option value="xxx">-----------请选择枚举类型-----------</option>
	{{#resultSet}}
   	 <option value={{code_key}}>{{code_name}}</option>
	{{/resultSet}}
  	</script>
  	<script type="text/x-mustache" id="error_msg_tpl">
    <div class="alert alert-danger alert-dismissable">
        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
        <h4><i class="icon fa fa-ban"></i> 执行失败!</h4>
		{{errorCode}}&nbsp;&nbsp;&nbsp;&nbsp;{{msg}}
    </div>
  	</script>
  	<script type="text/x-mustache" id="success_msg_tpl">
    <div class="alert alert-success alert-dismissable">
        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
        <h4>	<i class="icon fa fa-check"></i> 执行成功!</h4>
    </div>
  	</script>
  </body>
</html>
