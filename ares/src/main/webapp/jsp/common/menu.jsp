<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
      <!-- Left side column. contains the logo and sidebar -->
      <aside class="main-sidebar">
        <!-- sidebar: style can be found in sidebar.less -->
        <section class="sidebar">
          <!-- Sidebar user panel -->
          <div class="user-panel">
            <div class="pull-left image">
              <img src="<%=request.getContextPath()%>/images/AdminLTE/user2-160x160.jpg" class="img-circle" alt="User Image">
            </div>
            <div class="pull-left info">
              <p>Wenhe Zhang</p>
              <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
            </div>
          </div>
          <!-- search form -->
          <form action="#" method="get" class="sidebar-form">
            <div class="input-group">
              <input type="text" name="q" class="form-control" placeholder="Search...">
              <span class="input-group-btn">
                <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i></button>
              </span>
            </div>
          </form>
          <!-- /.search form -->
          <!-- sidebar menu: : style can be found in sidebar.less -->
          <!-- 
          <ul class="sidebar-menu">
            <li class="header">前台功能</li>
            <li class="treeview">
              <a href="#">
                <i class="fa fa-dashboard"></i> <span>图片处理</span> <i class="fa fa-angle-left pull-right"></i>
              </a>
              <ul class="treeview-menu">
                <li><a href="request.getContextPath()%>/picoperation/uploadPicView"><i class="fa fa-circle-o"></i>上传图片</a></li>
              </ul>
            </li>
          </ul>
           -->
          <ul class="sidebar-menu">
            <li class="header">QTI</li>
            <li class="treeview">
              <a href="#">
                <i class="fa fa-dashboard"></i> <span>Question</span> <i class="fa fa-angle-left pull-right"></i>
              </a>
              <ul class="treeview-menu">
                <li><a href="<%=request.getContextPath()%>/question/listQuestionView"><i class="fa fa-circle-o"></i>Question List</a></li>
              </ul>
              <ul class="treeview-menu">
                <li><a href="<%=request.getContextPath()%>/question/newQuestionView"><i class="fa fa-circle-o"></i>New Question</a></li>
              </ul>
            </li>
          </ul>
        </section>
        <!-- /.sidebar -->
      </aside>