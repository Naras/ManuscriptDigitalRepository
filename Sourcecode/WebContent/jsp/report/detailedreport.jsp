<%--
  Created by IntelliJ IDEA.
  User: lakshmi
  Date: 5/11/17
  Time: 9:56 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="com.indven.framework.util.IndvenApplicationConstants" %>
<%@ page import="java.util.ResourceBundle" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="com.indven.framework.util.CustomBeanUtil" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <style>
        body {
            background: rgb(204,204,204);
        }
        page {
            background: white;
            display: block;
            margin: 0 auto;
            margin-bottom: 0.5cm;
            box-shadow: 0 0 0.5cm rgba(0,0,0,0.5);
        }
        page[size="A4"] {
            width: 21cm;
            height: 29.7cm;
        }
        page[size="A4"][layout="portrait"] {
            width: 29.7cm;
            height: 21cm;
        }
        page[size="A3"] {
            width: 29.7cm;
            height: 42cm;
        }
        page[size="A3"][layout="portrait"] {
            width: 42cm;
            height: 29.7cm;
        }
        page[size="A5"] {
            width: 14.8cm;
            height: 21cm;
        }
        page[size="A5"][layout="portrait"] {
            width: 21cm;
            height: 14.8cm;
        }
        @media print {
            body, page {
                margin: 0;
                box-shadow: 0;
            }
        }
    </style>
</head>
<body>
<%
    List<HashMap<String,String>> searchDocumentList= (List<HashMap<String,String>>)request.getAttribute("searchDocumentList");
    System.out.println("searchDocumentList -------- "+searchDocumentList.size());
    for (int i=0;i<searchDocumentList.size();i++) {
        HashMap<String,String> rowObjectMap = searchDocumentList.get(i);
%>

    <page size="A4">
        <table>
            <tbody>
            <tr>
                <td id="Print" align="center">
                    <table border="0" width="100%" cellspacing="0" cellpadding="0">
                        <tbody>
                        <tr>
                            <td width="50%">&nbsp;</td>
                            <td align="center">
                                <table class="jrPage" style="empty-cells: show; width: 595px; border-collapse: collapse; background-color: white;" border="0" cellspacing="0" cellpadding="0">
                                    <tbody>
                                    <tr style="height: 0;" valign="top">
                                        <td style="width: 20px;">&nbsp;</td>
                                        <td style="width: 435px;">&nbsp;</td>
                                        <td style="width: 80px;">&nbsp;</td>
                                        <td style="width: 40px;">&nbsp;</td>
                                        <td style="width: 20px;">&nbsp;</td>
                                    </tr>
                                    <tr style="height: 20px;" valign="top">
                                        <td colspan="5">&nbsp;</td>
                                    </tr>
                                    <tr style="height: 82px;" valign="top">
                                        <td>&nbsp;</td>
                                        <td colspan="3">
                                            <div style="width: 100%; height: 100%; position: relative;">
                                                <div style="position: absolute; overflow: hidden; width: 100%; height: 100%;">
                                                    <table style="empty-cells: show; width: 100%; border-collapse: collapse;" border="0" cellspacing="0" cellpadding="0">
                                                        <tbody>
                                                        <tr style="height: 0;" valign="top">
                                                            <td style="width: 555px;">&nbsp;</td>
                                                        </tr>
                                                        <tr style="height: 82px;" valign="top">
                                                            <td style="background-color: #ffffff; border: 1px solid #000000;">&nbsp;</td>
                                                        </tr>
                                                        </tbody>
                                                    </table>
                                                </div>
                                                <div style="position: relative; width: 100%; height: 100%;">
                                                    <table style="empty-cells: show; width: 100%; border-collapse: collapse;" border="0" cellspacing="0" cellpadding="0">
                                                        <tbody>
                                                        <tr style="height: 0;" valign="top">
                                                            <td style="width: 2px;">&nbsp;</td>
                                                            <td style="width: 100px;">&nbsp;</td>
                                                            <td style="width: 164px;">&nbsp;</td>
                                                            <td style="width: 3px;">&nbsp;</td>
                                                            <td style="width: 110px;">&nbsp;</td>
                                                            <td style="width: 174px;">&nbsp;</td>
                                                            <td style="width: 2px;">&nbsp;</td>
                                                        </tr>
                                                        <tr style="height: 1px;" valign="top">
                                                            <td colspan="7">&nbsp;</td>
                                                        </tr>
                                                        <tr style="height: 20px;" valign="top">
                                                            <td>&nbsp;</td>
                                                            <td style="background-color: #c0c0c0; text-indent: 0px; text-align: left;" colspan="5"><span style="font-family: SansSerif; color: #000000; font-size: 14px; line-height: 1.1635742; font-weight: bold; text-decoration: underline;">Information</span></td>
                                                            <td>&nbsp;</td>
                                                        </tr>
                                                        <tr style="height: 1px;" valign="top">
                                                            <td colspan="7">&nbsp;</td>
                                                        </tr>
                                                        <tr style="height: 20px;" valign="top">
                                                            <td>&nbsp;</td>
                                                            <td style="text-indent: 0px; text-align: left;"><span style="font-family: SansSerif; color: #000000; font-size: 10px; line-height: 1.1635742; font-weight: bold;">Document ID :</span></td>
                                                            <td style="text-indent: 0px; text-align: left;">&nbsp;</td>
                                                            <td>&nbsp;</td>
                                                            <td style="text-indent: 0px; text-align: left;"><span style="font-family: SansSerif; color: #000000; font-size: 10px; line-height: 1.1635742; font-weight: bold;">Document Name :</span></td>

                                                            <td style="text-indent: 0px; text-align: left;"><span style="font-family: SansSerif; color: #000000; font-size: 10px; line-height: 1.1635742;"><%=CustomBeanUtil.getValueFromStringStringHashMap(rowObjectMap,"NAME")%></span></td>
                                                            <td>&nbsp;</td>
                                                        </tr>
                                                        <tr style="height: 20px;" valign="top">
                                                            <td>&nbsp;</td>
                                                            <td style="text-indent: 0px; text-align: left;"><span style="font-family: SansSerif; color: #000000; font-size: 10px; line-height: 1.1635742; font-weight: bold;">Accession No. :</span></td>
                                                            <td style="text-indent: 0px; text-align: left;"><span style="font-family: SansSerif; color: #000000; font-size: 10px; line-height: 1.1635742;">F16-1-1</span></td>
                                                            <td>&nbsp;</td>
                                                            <td style="text-indent: 0px; text-align: left;"><span style="font-family: SansSerif; color: #000000; font-size: 10px; line-height: 1.1635742; font-weight: bold;">Diacritical Name :</span></td>
                                                            <td style="text-indent: 0px; text-align: left;"><span style="font-family: SansSerif; color: #000000; font-size: 10px; line-height: 1.1635742;">gurunanḍanayogamāhātmyam</span></td>
                                                            <td>&nbsp;</td>
                                                        </tr>
                                                        <tr style="height: 20px;" valign="top">
                                                            <td>&nbsp;</td>
                                                            <td style="text-indent: 0px; text-align: left;"><span style="font-family: SansSerif; color: #000000; font-size: 10px; line-height: 1.1635742; font-weight: bold;">Bundle :</span></td>
                                                            <td style="text-indent: 0px; text-align: left;"><span style="font-family: SansSerif; color: #000000; font-size: 10px; line-height: 1.1635742;">N/A</span></td>
                                                            <td>&nbsp;</td>
                                                            <td style="text-indent: 0px; text-align: left;"><span style="font-family: SansSerif; color: #000000; font-size: 10px; line-height: 1.1635742; font-weight: bold;">Vernacular Name :</span></td>
                                                            <td style="text-indent: 0px; text-align: left;"><span style="font-family: SansSerif; color: #000000; font-size: 10px; line-height: 1.1635742;">गुरुनन्दनयोगमाहात्म्यम्</span></td>
                                                            <td>&nbsp;</td>
                                                        </tr>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                        </td>
                                        <td>&nbsp;</td>
                                    </tr>
                                    <tr style="height: 2px;" valign="top">
                                        <td colspan="5">&nbsp;</td>
                                    </tr>
                                    <tr style="height: 62px;" valign="top">
                                        <td>&nbsp;</td>
                                        <td colspan="3">
                                            <div style="width: 100%; height: 100%; position: relative;">
                                                <div style="position: absolute; overflow: hidden; width: 100%; height: 100%;">
                                                    <table style="empty-cells: show; width: 100%; border-collapse: collapse;" border="0" cellspacing="0" cellpadding="0">
                                                        <tbody>
                                                        <tr style="height: 0;" valign="top">
                                                            <td style="width: 555px;">&nbsp;</td>
                                                        </tr>
                                                        <tr style="height: 62px;" valign="top">
                                                            <td style="background-color: #ffffff; border: 1px solid #000000;">&nbsp;</td>
                                                        </tr>
                                                        </tbody>
                                                    </table>
                                                </div>
                                                <div style="position: relative; width: 100%; height: 100%;">
                                                    <table style="empty-cells: show; width: 100%; border-collapse: collapse;" border="0" cellspacing="0" cellpadding="0">
                                                        <tbody>
                                                        <tr style="height: 0;" valign="top">
                                                            <td style="width: 2px;">&nbsp;</td>
                                                            <td style="width: 70px;">&nbsp;</td>
                                                            <td style="width: 100px;">&nbsp;</td>
                                                            <td style="width: 45px;">&nbsp;</td>
                                                            <td style="width: 69px;">&nbsp;</td>
                                                            <td style="width: 93px;">&nbsp;</td>
                                                            <td style="width: 174px;">&nbsp;</td>
                                                            <td style="width: 2px;">&nbsp;</td>
                                                        </tr>
                                                        <tr style="height: 1px;" valign="top">
                                                            <td colspan="8">&nbsp;</td>
                                                        </tr>
                                                        <tr style="height: 20px;" valign="top">
                                                            <td style="background-color: #c0c0c0; text-indent: 0px; text-align: left;" colspan="7"><span style="font-family: SansSerif; color: #000000; font-size: 14px; line-height: 1.1635742; font-weight: bold; text-decoration: underline;">Work Details</span></td>
                                                            <td>&nbsp;</td>
                                                        </tr>
                                                        <tr style="height: 1px;" valign="top">
                                                            <td colspan="8">&nbsp;</td>
                                                        </tr>
                                                        <tr style="height: 20px;" valign="top">
                                                            <td>&nbsp;</td>
                                                            <td style="text-indent: 0px; text-align: left;"><span style="font-family: SansSerif; color: #000000; font-size: 10px; line-height: 1.1635742; font-weight: bold;">Language :</span></td>
                                                            <td style="text-indent: 0px; text-align: left;"><span style="font-family: SansSerif; color: #000000; font-size: 10px; line-height: 1.1635742;">Sanskrit</span></td>
                                                            <td style="text-indent: 0px; text-align: left;"><span style="font-family: SansSerif; color: #000000; font-size: 10px; line-height: 1.1635742; font-weight: bold;">Script :</span></td>
                                                            <td style="text-indent: 0px; text-align: left;"><span style="font-family: SansSerif; color: #000000; font-size: 10px; line-height: 1.1635742;">Devanagari</span></td>
                                                            <td style="text-indent: 0px; text-align: left;"><span style="font-family: SansSerif; color: #000000; font-size: 10px; line-height: 1.1635742; font-weight: bold;">Subject :</span></td>
                                                            <td style="text-indent: 0px; text-align: left;"><span style="font-family: SansSerif; color: #000000; font-size: 10px; line-height: 1.1635742;">Vaidika yoga manuscr with image</span></td>
                                                            <td>&nbsp;</td>
                                                        </tr>
                                                        <tr style="height: 20px;" valign="top">
                                                            <td>&nbsp;</td>
                                                            <td style="text-indent: 0px; text-align: left;"><span style="font-family: SansSerif; color: #000000; font-size: 10px; line-height: 1.1635742; font-weight: bold;">Material :</span></td>
                                                            <td style="text-indent: 0px; text-align: left;"><span style="font-family: SansSerif; color: #000000; font-size: 10px; line-height: 1.1635742;">Paper</span></td>
                                                            <td style="text-indent: 0px; text-align: left;"><span style="font-family: SansSerif; color: #000000; font-size: 10px; line-height: 1.1635742; font-weight: bold;">Type :</span></td>
                                                            <td style="text-indent: 0px; text-align: left;"><span style="font-family: SansSerif; color: #000000; font-size: 10px; line-height: 1.1635742;">Poetry</span></td>
                                                            <td style="text-indent: 0px; text-align: left;"><span style="font-family: SansSerif; color: #000000; font-size: 10px; line-height: 1.1635742; font-weight: bold;">Specific Category </span></td>
                                                            <td style="text-indent: 0px; text-align: left;"><span style="font-family: SansSerif; color: #000000; font-size: 10px; line-height: 1.1635742;">Commentary,Original work,Sub-</span></td>
                                                            <td>&nbsp;</td>
                                                        </tr>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                        </td>
                                        <td>&nbsp;</td>
                                    </tr>
                                    <tr style="height: 2px;" valign="top">
                                        <td colspan="5">&nbsp;</td>
                                    </tr>
                                    <tr style="height: 112px;" valign="top">
                                        <td>&nbsp;</td>
                                        <td colspan="3">
                                            <div style="width: 100%; height: 100%; position: relative;">
                                                <div style="position: absolute; overflow: hidden; width: 100%; height: 100%;">
                                                    <table style="empty-cells: show; width: 100%; border-collapse: collapse;" border="0" cellspacing="0" cellpadding="0">
                                                        <tbody>
                                                        <tr style="height: 0;" valign="top">
                                                            <td style="width: 555px;">&nbsp;</td>
                                                        </tr>
                                                        <tr style="height: 112px;" valign="top">
                                                            <td style="background-color: #ffffff; border: 1px solid #000000;">&nbsp;</td>
                                                        </tr>
                                                        </tbody>
                                                    </table>
                                                </div>
                                                <div style="position: relative; width: 100%; height: 100%;">
                                                    <table style="empty-cells: show; width: 100%; border-collapse: collapse;" border="0" cellspacing="0" cellpadding="0">
                                                        <tbody>
                                                        <tr style="height: 0;" valign="top">
                                                            <td style="width: 2px;">&nbsp;</td>
                                                            <td style="width: 264px;">&nbsp;</td>
                                                            <td style="width: 3px;">&nbsp;</td>
                                                            <td style="width: 284px;">&nbsp;</td>
                                                            <td style="width: 2px;">&nbsp;</td>
                                                        </tr>
                                                        <tr style="height: 1px;" valign="top">
                                                            <td colspan="5">&nbsp;</td>
                                                        </tr>
                                                        <tr style="height: 18px;" valign="top">
                                                            <td>&nbsp;</td>
                                                            <td style="background-color: #c0c0c0; text-indent: 0px; text-align: left;"><span style="font-family: SansSerif; color: #000000; font-size: 10px; line-height: 1.1635742; font-weight: bold;">Summary :</span></td>
                                                            <td>&nbsp;</td>
                                                            <td style="background-color: #c0c0c0; text-indent: 0px; text-align: left;"><span style="font-family: SansSerif; color: #000000; font-size: 10px; line-height: 1.1635742; font-weight: bold;">Table Of Contents :</span></td>
                                                            <td>&nbsp;</td>
                                                        </tr>
                                                        <tr style="height: 40px;" valign="top">
                                                            <td>&nbsp;</td>
                                                            <td style="text-indent: 0px; text-align: left;">&nbsp;</td>
                                                            <td>&nbsp;</td>
                                                            <td style="text-indent: 0px; text-align: left;">&nbsp;</td>
                                                            <td>&nbsp;</td>
                                                        </tr>
                                                        <tr style="height: 20px;" valign="top">
                                                            <td>&nbsp;</td>
                                                            <td style="background-color: #c0c0c0; text-indent: 0px; text-align: left;"><span style="font-family: SansSerif; color: #000000; font-size: 10px; line-height: 1.1635742; font-weight: bold;">Authors:</span></td>
                                                            <td>&nbsp;</td>
                                                            <td style="background-color: #c0c0c0; text-indent: 0px; text-align: left;"><span style="font-family: SansSerif; color: #000000; font-size: 10px; line-height: 1.1635742; font-weight: bold;">Scribe:</span></td>
                                                            <td>&nbsp;</td>
                                                        </tr>
                                                        <tr style="height: 33px;" valign="top">
                                                            <td>&nbsp;</td>
                                                            <td style="text-indent: 0px; text-align: left;"><span style="font-family: SansSerif; color: #000000; font-size: 10px; line-height: 1.1635742;">N/A</span></td>
                                                            <td>&nbsp;</td>
                                                            <td style="text-indent: 0px; text-align: left;"><span style="font-family: SansSerif; color: #000000; font-size: 10px; line-height: 1.1635742;">N/A</span></td>
                                                            <td>&nbsp;</td>
                                                        </tr>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                        </td>
                                        <td>&nbsp;</td>
                                    </tr>
                                    <tr style="height: 3px;" valign="top">
                                        <td colspan="5">&nbsp;</td>
                                    </tr>
                                    <tr style="height: 182px;" valign="top">
                                        <td>&nbsp;</td>
                                        <td colspan="3">
                                            <div style="width: 100%; height: 100%; position: relative;">
                                                <div style="position: absolute; overflow: hidden; width: 100%; height: 100%;">
                                                    <table style="empty-cells: show; width: 100%; border-collapse: collapse;" border="0" cellspacing="0" cellpadding="0">
                                                        <tbody>
                                                        <tr style="height: 0;" valign="top">
                                                            <td style="width: 555px;">&nbsp;</td>
                                                        </tr>
                                                        <tr style="height: 182px;" valign="top">
                                                            <td style="background-color: #ffffff; border: 1px solid #000000;">&nbsp;</td>
                                                        </tr>
                                                        </tbody>
                                                    </table>
                                                </div>
                                                <div style="position: relative; width: 100%; height: 100%;">
                                                    <table style="empty-cells: show; width: 100%; border-collapse: collapse;" border="0" cellspacing="0" cellpadding="0">
                                                        <tbody>
                                                        <tr style="height: 0;" valign="top">
                                                            <td style="width: 2px;">&nbsp;</td>
                                                            <td style="width: 551px;">&nbsp;</td>
                                                            <td style="width: 2px;">&nbsp;</td>
                                                        </tr>
                                                        <tr style="height: 1px;" valign="top">
                                                            <td colspan="3">&nbsp;</td>
                                                        </tr>
                                                        <tr style="height: 19px;" valign="top">
                                                            <td>&nbsp;</td>
                                                            <td style="background-color: #c0c0c0; text-indent: 0px; text-align: left;"><span style="font-family: SansSerif; color: #000000; font-size: 14px; line-height: 1.1635742; font-weight: bold; text-decoration: underline;">Frames</span></td>
                                                            <td>&nbsp;</td>
                                                        </tr>
                                                        <tr style="height: 162px;" valign="top">
                                                            <td colspan="2">
                                                                <div style="width: 100%; height: 100%; position: relative;">
                                                                    <div style="position: absolute; overflow: hidden; width: 100%; height: 100%;">
                                                                        <table style="empty-cells: show; width: 100%; border-collapse: collapse;" border="0" cellspacing="0" cellpadding="0">
                                                                            <tbody>
                                                                            <tr style="height: 0;" valign="top">
                                                                                <td style="width: 553px;">&nbsp;</td>
                                                                            </tr>
                                                                            <tr style="height: 162px;" valign="top">
                                                                                <td>&nbsp;</td>
                                                                            </tr>
                                                                            </tbody>
                                                                        </table>
                                                                    </div>
                                                                    <div style="position: relative; width: 100%; height: 100%;">
                                                                        <table style="empty-cells: show; width: 100%; border-collapse: collapse;" border="0" cellspacing="0" cellpadding="0">
                                                                            <tbody>
                                                                            <tr style="height: 0;" valign="top">
                                                                                <td style="width: 10px;">&nbsp;</td>
                                                                                <td style="width: 173px;">&nbsp;</td>
                                                                                <td style="width: 7px;">&nbsp;</td>
                                                                                <td style="width: 173px;">&nbsp;</td>
                                                                                <td style="width: 11px;">&nbsp;</td>
                                                                                <td style="width: 173px;">&nbsp;</td>
                                                                                <td style="width: 6px;">&nbsp;</td>
                                                                            </tr>
                                                                            <tr style="height: 1px;" valign="top">
                                                                                <td colspan="7">&nbsp;</td>
                                                                            </tr>
                                                                            <tr style="height: 160px;" valign="top">
                                                                                <td>&nbsp;</td>
                                                                                <td style="text-align: center; vertical-align: middle; border: 1px solid #000000;"><img style="height: 160px;" src="/root/mdr/media/img/2015/12/18/4/1450459804713171450459359559_abc-1.png" alt="" /></td>
                                                                                <td>&nbsp;</td>
                                                                                <td style="text-align: center; vertical-align: middle; border: 1px solid #000000;"><img style="height: 160px;" src="/root/mdr/media/img/2015/12/18/4/1450459803308171450459359559_abc-2.png" alt="" /></td>
                                                                                <td>&nbsp;</td>
                                                                                <td style="text-align: center; vertical-align: middle; border: 1px solid #000000;"><img style="height: 160px;" src="/root/mdr/media/img/2015/12/18/4/1450459800553171450459359559_abc-3.png" alt="" /></td>
                                                                                <td>&nbsp;</td>
                                                                            </tr>
                                                                            <tr style="height: 1px;" valign="top">
                                                                                <td colspan="7">&nbsp;</td>
                                                                            </tr>
                                                                            </tbody>
                                                                        </table>
                                                                    </div>
                                                                </div>
                                                            </td>
                                                            <td>&nbsp;</td>
                                                        </tr>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                        </td>
                                        <td>&nbsp;</td>
                                    </tr>
                                    <tr style="height: 2px;" valign="top">
                                        <td colspan="5">&nbsp;</td>
                                    </tr>
                                    <tr style="height: 141px;" valign="top">
                                        <td>&nbsp;</td>
                                        <td colspan="3">
                                            <div style="width: 100%; height: 100%; position: relative;">
                                                <div style="position: absolute; overflow: hidden; width: 100%; height: 100%;">
                                                    <table style="empty-cells: show; width: 100%; border-collapse: collapse;" border="0" cellspacing="0" cellpadding="0">
                                                        <tbody>
                                                        <tr style="height: 0;" valign="top">
                                                            <td style="width: 555px;">&nbsp;</td>
                                                        </tr>
                                                        <tr style="height: 141px;" valign="top">
                                                            <td style="background-color: #ffffff; border: 1px solid #000000;">&nbsp;</td>
                                                        </tr>
                                                        </tbody>
                                                    </table>
                                                </div>
                                                <div style="position: relative; width: 100%; height: 100%;">
                                                    <table style="empty-cells: show; width: 100%; border-collapse: collapse;" border="0" cellspacing="0" cellpadding="0">
                                                        <tbody>
                                                        <tr style="height: 0;" valign="top">
                                                            <td style="width: 2px;">&nbsp;</td>
                                                            <td style="width: 100px;">&nbsp;</td>
                                                            <td style="width: 164px;">&nbsp;</td>
                                                            <td style="width: 3px;">&nbsp;</td>
                                                            <td style="width: 110px;">&nbsp;</td>
                                                            <td style="width: 174px;">&nbsp;</td>
                                                            <td style="width: 2px;">&nbsp;</td>
                                                        </tr>
                                                        <tr style="height: 1px;" valign="top">
                                                            <td colspan="7">&nbsp;</td>
                                                        </tr>
                                                        <tr style="height: 20px;" valign="top">
                                                            <td>&nbsp;</td>
                                                            <td style="background-color: #c0c0c0; text-indent: 0px; text-align: left;" colspan="5"><span style="font-family: SansSerif; color: #000000; font-size: 14px; line-height: 1.1635742; font-weight: bold; text-decoration: underline;">Subject Details</span></td>
                                                            <td>&nbsp;</td>
                                                        </tr>
                                                        <tr style="height: 20px;" valign="top">
                                                            <td>&nbsp;</td>
                                                            <td style="text-indent: 0px; text-align: left;"><span style="font-family: SansSerif; color: #000000; font-size: 10px; line-height: 1.1635742; font-weight: bold;">Bound :</span></td>
                                                            <td style="text-indent: 0px; text-align: left;"><span style="font-family: SansSerif; color: #000000; font-size: 10px; line-height: 1.1635742;">No</span></td>
                                                            <td>&nbsp;</td>
                                                            <td style="text-indent: 0px; text-align: left;"><span style="font-family: SansSerif; color: #000000; font-size: 10px; line-height: 1.1635742; font-weight: bold;">Source Of Catalogue </span></td>
                                                            <td style="text-indent: 0px; text-align: left;"><span style="font-family: SansSerif; color: #000000; font-size: 10px; line-height: 1.1635742;">N/A</span></td>
                                                            <td>&nbsp;</td>
                                                        </tr>
                                                        <tr style="height: 20px;" valign="top">
                                                            <td>&nbsp;</td>
                                                            <td style="text-indent: 0px; text-align: left;"><span style="font-family: SansSerif; color: #000000; font-size: 10px; line-height: 1.1635742; font-weight: bold;">Catalogue No. :</span></td>
                                                            <td style="text-indent: 0px; text-align: left;">&nbsp;</td>
                                                            <td>&nbsp;</td>
                                                            <td style="text-indent: 0px; text-align: left;"><span style="font-family: SansSerif; color: #000000; font-size: 10px; line-height: 1.1635742; font-weight: bold;">Catalogue Details :</span></td>
                                                            <td style="text-indent: 0px; text-align: left;">&nbsp;</td>
                                                            <td>&nbsp;</td>
                                                        </tr>
                                                        <tr style="height: 20px;" valign="top">
                                                            <td>&nbsp;</td>
                                                            <td style="text-indent: 0px; text-align: left;"><span style="font-family: SansSerif; color: #000000; font-size: 10px; line-height: 1.1635742; font-weight: bold;">Digitized By. :</span></td>
                                                            <td style="text-indent: 0px; text-align: left;"><span style="font-family: SansSerif; color: #000000; font-size: 10px; line-height: 1.1635742;">N/A</span></td>
                                                            <td>&nbsp;</td>
                                                            <td style="text-indent: 0px; text-align: left;"><span style="font-family: SansSerif; color: #000000; font-size: 10px; line-height: 1.1635742; font-weight: bold;">No. Of Illustrations :</span></td>
                                                            <td style="text-indent: 0px; text-align: left;"><span style="font-family: SansSerif; color: #000000; font-size: 10px; line-height: 1.1635742;">0</span></td>
                                                            <td>&nbsp;</td>
                                                        </tr>
                                                        <tr style="height: 20px;" valign="top">
                                                            <td>&nbsp;</td>
                                                            <td style="text-indent: 0px; text-align: left;"><span style="font-family: SansSerif; color: #000000; font-size: 10px; line-height: 1.1635742; font-weight: bold;">Documentation :</span></td>
                                                            <td style="text-indent: 0px; text-align: left;"><span style="font-family: SansSerif; color: #000000; font-size: 10px; line-height: 1.1635742;">N/A</span></td>
                                                            <td>&nbsp;</td>
                                                            <td style="text-indent: 0px; text-align: left;"><span style="font-family: SansSerif; color: #000000; font-size: 10px; line-height: 1.1635742; font-weight: bold;">Begining Line :</span></td>
                                                            <td style="text-indent: 0px; text-align: left;"><span style="font-family: SansSerif; color: #000000; font-size: 10px; line-height: 1.1635742;">अथ गुरुनन्दनयोगमाह</span></td>
                                                            <td>&nbsp;</td>
                                                        </tr>
                                                        <tr style="height: 20px;" valign="top">
                                                            <td>&nbsp;</td>
                                                            <td style="text-indent: 0px; text-align: left;"><span style="font-family: SansSerif; color: #000000; font-size: 10px; line-height: 1.1635742; font-weight: bold;">No Of Folies :</span></td>
                                                            <td style="text-indent: 0px; text-align: left;"><span style="font-family: SansSerif; color: #000000; font-size: 10px; line-height: 1.1635742;">null</span></td>
                                                            <td>&nbsp;</td>
                                                            <td style="text-indent: 0px; text-align: left;"><span style="font-family: SansSerif; color: #000000; font-size: 10px; line-height: 1.1635742; font-weight: bold;">Ending Line :</span></td>
                                                            <td style="text-indent: 0px; text-align: left;"><span style="font-family: SansSerif; color: #000000; font-size: 10px; line-height: 1.1635742;">मनक्षेत्रे व्याघातै च</span></td>
                                                            <td>&nbsp;</td>
                                                        </tr>
                                                        <tr style="height: 20px;" valign="top">
                                                            <td>&nbsp;</td>
                                                            <td style="text-indent: 0px; text-align: left;"><span style="font-family: SansSerif; color: #000000; font-size: 10px; line-height: 1.1635742; font-weight: bold;">Colophone :</span></td>
                                                            <td style="text-indent: 0px; text-align: left;">&nbsp;</td>
                                                            <td>&nbsp;</td>
                                                            <td style="text-indent: 0px; text-align: left;"><span style="font-family: SansSerif; color: #000000; font-size: 10px; line-height: 1.1635742; font-weight: bold;">Manuscript </span></td>
                                                            <td style="text-indent: 0px; text-align: left;"><span style="font-family: SansSerif; color: #000000; font-size: 10px; line-height: 1.1635742;">Good</span></td>
                                                            <td>&nbsp;</td>
                                                        </tr>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                        </td>
                                        <td>&nbsp;</td>
                                    </tr>
                                    <tr style="height: 2px;" valign="top">
                                        <td colspan="5">&nbsp;</td>
                                    </tr>
                                    <tr style="height: 160px;" valign="top">
                                        <td>&nbsp;</td>
                                        <td colspan="3">
                                            <div style="width: 100%; height: 100%; position: relative;">
                                                <div style="position: absolute; overflow: hidden; width: 100%; height: 100%;">
                                                    <table style="empty-cells: show; width: 100%; border-collapse: collapse;" border="0" cellspacing="0" cellpadding="0">
                                                        <tbody>
                                                        <tr style="height: 0;" valign="top">
                                                            <td style="width: 555px;">&nbsp;</td>
                                                        </tr>
                                                        <tr style="height: 160px;" valign="top">
                                                            <td style="background-color: #ffffff; border: 1px solid #000000;">&nbsp;</td>
                                                        </tr>
                                                        </tbody>
                                                    </table>
                                                </div>
                                                <div style="position: relative; width: 100%; height: 100%;">
                                                    <table style="empty-cells: show; width: 100%; border-collapse: collapse;" border="0" cellspacing="0" cellpadding="0">
                                                        <tbody>
                                                        <tr style="height: 0;" valign="top">
                                                            <td style="width: 2px;">&nbsp;</td>
                                                            <td style="width: 70px;">&nbsp;</td>
                                                            <td style="width: 194px;">&nbsp;</td>
                                                            <td style="width: 3px;">&nbsp;</td>
                                                            <td style="width: 70px;">&nbsp;</td>
                                                            <td style="width: 91px;">&nbsp;</td>
                                                            <td style="width: 47px;">&nbsp;</td>
                                                            <td style="width: 76px;">&nbsp;</td>
                                                            <td style="width: 2px;">&nbsp;</td>
                                                        </tr>
                                                        <tr style="height: 1px;" valign="top">
                                                            <td colspan="9">&nbsp;</td>
                                                        </tr>
                                                        <tr style="height: 20px;" valign="top">
                                                            <td>&nbsp;</td>
                                                            <td style="background-color: #c0c0c0; text-indent: 0px; text-align: left;" colspan="2"><span style="font-family: SansSerif; color: #000000; font-size: 14px; line-height: 1.1635742; font-weight: bold; text-decoration: underline;">Source Details</span></td>
                                                            <td>&nbsp;</td>
                                                            <td style="background-color: #c0c0c0; text-indent: 0px; text-align: left;" colspan="4"><span style="font-family: SansSerif; color: #000000; font-size: 14px; line-height: 1.1635742; font-weight: bold; text-decoration: underline;">Publication Details</span></td>
                                                            <td>&nbsp;</td>
                                                        </tr>
                                                        <tr style="height: 20px;" valign="top">
                                                            <td>&nbsp;</td>
                                                            <td style="text-indent: 0px; text-align: left;"><span style="font-family: SansSerif; color: #000000; font-size: 10px; line-height: 1.1635742; font-weight: bold;">Name :</span></td>
                                                            <td style="text-indent: 0px; text-align: left;"><span style="font-family: SansSerif; color: #000000; font-size: 10px; line-height: 1.1635742;">Anandashrama</span></td>
                                                            <td>&nbsp;</td>
                                                            <td style="text-indent: 0px; text-align: left;"><span style="font-family: SansSerif; color: #000000; font-size: 10px; line-height: 1.1635742; font-weight: bold;">Name :</span></td>
                                                            <td style="text-indent: 0px; text-align: left;" colspan="3"><span style="font-family: SansSerif; color: #000000; font-size: 10px; line-height: 1.1635742;">N/A</span></td>
                                                            <td>&nbsp;</td>
                                                        </tr>
                                                        <tr style="height: 20px;" valign="top">
                                                            <td>&nbsp;</td>
                                                            <td style="text-indent: 0px; text-align: left;"><span style="font-family: SansSerif; color: #000000; font-size: 10px; line-height: 1.1635742; font-weight: bold;">Address :</span></td>
                                                            <td style="text-indent: 0px; text-align: left;" rowspan="2"><span style="font-family: SansSerif; color: #000000; font-size: 10px; line-height: 1.1635742;">Bajirao Rd, Budhwar Peth, Pune, Maharashtra 411030</span></td>
                                                            <td>&nbsp;</td>
                                                            <td style="text-indent: 0px; text-align: left;"><span style="font-family: SansSerif; color: #000000; font-size: 10px; line-height: 1.1635742; font-weight: bold;">Address :</span></td>
                                                            <td style="text-indent: 0px; text-align: left;" colspan="3" rowspan="2"><span style="font-family: SansSerif; color: #000000; font-size: 10px; line-height: 1.1635742;">N/A</span></td>
                                                            <td>&nbsp;</td>
                                                        </tr>
                                                        <tr style="height: 39px;" valign="top">
                                                            <td colspan="2">&nbsp;</td>
                                                            <td colspan="2">&nbsp;</td>
                                                            <td>&nbsp;</td>
                                                        </tr>
                                                        <tr style="height: 20px;" valign="top">
                                                            <td>&nbsp;</td>
                                                            <td style="text-indent: 0px; text-align: left;"><span style="font-family: SansSerif; color: #000000; font-size: 10px; line-height: 1.1635742; font-weight: bold;">Phone No.:</span></td>
                                                            <td style="text-indent: 0px; text-align: left;">&nbsp;</td>
                                                            <td>&nbsp;</td>
                                                            <td style="text-indent: 0px; text-align: left;"><span style="font-family: SansSerif; color: #000000; font-size: 10px; line-height: 1.1635742; font-weight: bold;">Editer Name :</span></td>
                                                            <td style="text-indent: 0px; text-align: left;" colspan="3"><span style="font-family: SansSerif; color: #000000; font-size: 10px; line-height: 1.1635742;">N/A</span></td>
                                                            <td>&nbsp;</td>
                                                        </tr>
                                                        <tr style="height: 20px;" valign="top">
                                                            <td>&nbsp;</td>
                                                            <td style="text-indent: 0px; text-align: left;"><span style="font-family: SansSerif; color: #000000; font-size: 10px; line-height: 1.1635742; font-weight: bold;">Email ID :</span></td>
                                                            <td style="text-indent: 0px; text-align: left;">&nbsp;</td>
                                                            <td>&nbsp;</td>
                                                            <td style="text-indent: 0px; text-align: left;"><span style="font-family: SansSerif; color: #000000; font-size: 10px; line-height: 1.1635742; font-weight: bold;">No Of Pages :</span></td>
                                                            <td style="text-indent: 0px; text-align: left;"><span style="font-family: SansSerif; color: #000000; font-size: 10px; line-height: 1.1635742;">N/A</span></td>
                                                            <td style="text-indent: 0px; text-align: left;"><span style="font-family: SansSerif; color: #000000; font-size: 10px; line-height: 1.1635742; font-weight: bold;">Price :</span></td>
                                                            <td style="text-indent: 0px; text-align: left;"><span style="font-family: SansSerif; color: #000000; font-size: 10px; line-height: 1.1635742;">N/A</span></td>
                                                            <td>&nbsp;</td>
                                                        </tr>
                                                        <tr style="height: 20px;" valign="top">
                                                            <td>&nbsp;</td>
                                                            <td style="text-indent: 0px; text-align: left;"><span style="font-family: SansSerif; color: #000000; font-size: 10px; line-height: 1.1635742; font-weight: bold;">Website :</span></td>
                                                            <td style="text-indent: 0px; text-align: left;"><span style="font-family: SansSerif; color: #000000; font-size: 10px; line-height: 1.1635742;">http://aanandashram-sanstha.</span></td>
                                                            <td>&nbsp;</td>
                                                            <td style="text-indent: 0px; text-align: left;"><span style="font-family: SansSerif; color: #000000; font-size: 10px; line-height: 1.1635742; font-weight: bold;">Year :</span></td>
                                                            <td style="text-indent: 0px; text-align: left;"><span style="font-family: SansSerif; color: #000000; font-size: 10px; line-height: 1.1635742;">N/A</span></td>
                                                            <td colspan="3">&nbsp;</td>
                                                        </tr>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                        </td>
                                        <td>&nbsp;</td>
                                    </tr>
                                    <tr style="height: 22px;" valign="top">
                                        <td colspan="5">&nbsp;</td>
                                    </tr>
                                    <tr style="height: 20px;" valign="top">
                                        <td colspan="2">&nbsp;</td>
                                        <td style="text-indent: 0px; text-align: right;"><span style="font-family: SansSerif; color: #000000; font-size: 12px; line-height: 1.1635742; font-weight: bold;">Page 2 of</span></td>
                                        <td style="text-indent: 0px; text-align: left;"><span style="font-family: SansSerif; color: #000000; font-size: 12px; line-height: 1.1635742; font-weight: bold;">&nbsp;4227</span></td>
                                        <td>&nbsp;</td>
                                    </tr>
                                    <tr style="height: 30px;" valign="top">
                                        <td colspan="5">&nbsp;</td>
                                    </tr>
                                    </tbody>
                                </table>
                            </td>
                            <td width="50%">&nbsp;</td>
                        </tr>
                        </tbody>
                    </table>
                </td>
            </tr>
            <tr>
                <td align="center"><button class="cancel-button" style="height: 30px; width: 90px;">Cancel</button></td>
            </tr>
            </tbody>
        </table>
        <p>&nbsp;</p>
    </page>
<%
    }
%>
</body>
</html>
