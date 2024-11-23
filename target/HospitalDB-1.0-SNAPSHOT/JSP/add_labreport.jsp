<%-- Document : add_labreport Created on : Nov 18, 2024, 2:14:52 PM Author :
Windows User --%> 
<%@page contentType="text/html" pageEncoding="UTF-8"%> <%@page
import="java.math.BigDecimal" %> <%@page import="java.sql.Date" %> <%@page
import="java.sql.Timestamp" %> <%@page
import="java.time.Year" %> <%@page import="java.text.ParseException" %> <%@page
import="java.text.SimpleDateFormat" %> <%@page import="java.util.*" %> <%@page
import="com.source.HospitalDB.Classes.*" %> <%@page
import="com.source.HospitalDB.DAO.*" %> <%@page
import="java.math.BigDecimal" %> 
<%@ page import="com.source.HospitalDB.App" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>MediBASE: Laboratory Test Request</title>
    <link rel="stylesheet" href="style/new_laboratory.css" />
  </head>
    
    <style>
      /* vietnamese */
@font-face {
  font-family: 'Space Grotesk';
  font-style: normal;
  font-weight: 600;
  font-display: block;
  src: url(https://design.penpot.app/internal/gfonts/font/spacegrotesk/v16/V8mQoQDjQSkFtoMM3T6r8E7mF71Q-gOoraIAEj42VnsrPMBTTA.woff2) format('woff2');
  unicode-range: U+0102-0103, U+0110-0111, U+0128-0129, U+0168-0169, U+01A0-01A1, U+01AF-01B0, U+0300-0301, U+0303-0304, U+0308-0309, U+0323, U+0329, U+1EA0-1EF9, U+20AB;
}
/* latin-ext */
@font-face {
  font-family: 'Space Grotesk';
  font-style: normal;
  font-weight: 600;
  font-display: block;
  src: url(https://design.penpot.app/internal/gfonts/font/spacegrotesk/v16/V8mQoQDjQSkFtoMM3T6r8E7mF71Q-gOoraIAEj42VnsqPMBTTA.woff2) format('woff2');
  unicode-range: U+0100-02BA, U+02BD-02C5, U+02C7-02CC, U+02CE-02D7, U+02DD-02FF, U+0304, U+0308, U+0329, U+1D00-1DBF, U+1E00-1E9F, U+1EF2-1EFF, U+2020, U+20A0-20AB, U+20AD-20C0, U+2113, U+2C60-2C7F, U+A720-A7FF;
}
/* latin */
@font-face {
  font-family: 'Space Grotesk';
  font-style: normal;
  font-weight: 600;
  font-display: block;
  src: url(https://design.penpot.app/internal/gfonts/font/spacegrotesk/v16/V8mQoQDjQSkFtoMM3T6r8E7mF71Q-gOoraIAEj42VnskPMA.woff2) format('woff2');
  unicode-range: U+0000-00FF, U+0131, U+0152-0153, U+02BB-02BC, U+02C6, U+02DA, U+02DC, U+0304, U+0308, U+0329, U+2000-206F, U+20AC, U+2122, U+2191, U+2193, U+2212, U+2215, U+FEFF, U+FFFD;
}

/* vietnamese */
@font-face {
  font-family: 'Space Grotesk';
  font-style: normal;
  font-weight: 400;
  font-display: block;
  src: url(https://design.penpot.app/internal/gfonts/font/spacegrotesk/v16/V8mQoQDjQSkFtoMM3T6r8E7mF71Q-gOoraIAEj7oUXsrPMBTTA.woff2) format('woff2');
  unicode-range: U+0102-0103, U+0110-0111, U+0128-0129, U+0168-0169, U+01A0-01A1, U+01AF-01B0, U+0300-0301, U+0303-0304, U+0308-0309, U+0323, U+0329, U+1EA0-1EF9, U+20AB;
}
/* latin-ext */
@font-face {
  font-family: 'Space Grotesk';
  font-style: normal;
  font-weight: 400;
  font-display: block;
  src: url(https://design.penpot.app/internal/gfonts/font/spacegrotesk/v16/V8mQoQDjQSkFtoMM3T6r8E7mF71Q-gOoraIAEj7oUXsqPMBTTA.woff2) format('woff2');
  unicode-range: U+0100-02BA, U+02BD-02C5, U+02C7-02CC, U+02CE-02D7, U+02DD-02FF, U+0304, U+0308, U+0329, U+1D00-1DBF, U+1E00-1E9F, U+1EF2-1EFF, U+2020, U+20A0-20AB, U+20AD-20C0, U+2113, U+2C60-2C7F, U+A720-A7FF;
}
/* latin */
@font-face {
  font-family: 'Space Grotesk';
  font-style: normal;
  font-weight: 400;
  font-display: block;
  src: url(https://design.penpot.app/internal/gfonts/font/spacegrotesk/v16/V8mQoQDjQSkFtoMM3T6r8E7mF71Q-gOoraIAEj7oUXskPMA.woff2) format('woff2');
  unicode-range: U+0000-00FF, U+0131, U+0152-0153, U+02BB-02BC, U+02C6, U+02DA, U+02DC, U+0304, U+0308, U+0329, U+2000-206F, U+20AC, U+2122, U+2191, U+2193, U+2212, U+2215, U+FEFF, U+FFFD;
}

/* cyrillic-ext */
@font-face {
  font-family: 'Noto Sans';
  font-style: normal;
  font-weight: 600;
  font-stretch: 100%;
  font-display: block;
  src: url(https://design.penpot.app/internal/gfonts/font/notosans/v37/o-0mIpQlx3QUlC5A4PNB6Ryti20_6n1iPHjcz6L1SoM-jCpoiyAjBO9X6VLKzA.woff2) format('woff2');
  unicode-range: U+0460-052F, U+1C80-1C8A, U+20B4, U+2DE0-2DFF, U+A640-A69F, U+FE2E-FE2F;
}
/* cyrillic */
@font-face {
  font-family: 'Noto Sans';
  font-style: normal;
  font-weight: 600;
  font-stretch: 100%;
  font-display: block;
  src: url(https://design.penpot.app/internal/gfonts/font/notosans/v37/o-0mIpQlx3QUlC5A4PNB6Ryti20_6n1iPHjcz6L1SoM-jCpoiyAjBO9e6VLKzA.woff2) format('woff2');
  unicode-range: U+0301, U+0400-045F, U+0490-0491, U+04B0-04B1, U+2116;
}
/* devanagari */
@font-face {
  font-family: 'Noto Sans';
  font-style: normal;
  font-weight: 600;
  font-stretch: 100%;
  font-display: block;
  src: url(https://design.penpot.app/internal/gfonts/font/notosans/v37/o-0mIpQlx3QUlC5A4PNB6Ryti20_6n1iPHjcz6L1SoM-jCpoiyAjBO9b6VLKzA.woff2) format('woff2');
  unicode-range: U+0900-097F, U+1CD0-1CF9, U+200C-200D, U+20A8, U+20B9, U+20F0, U+25CC, U+A830-A839, U+A8E0-A8FF, U+11B00-11B09;
}
/* greek-ext */
@font-face {
  font-family: 'Noto Sans';
  font-style: normal;
  font-weight: 600;
  font-stretch: 100%;
  font-display: block;
  src: url(https://design.penpot.app/internal/gfonts/font/notosans/v37/o-0mIpQlx3QUlC5A4PNB6Ryti20_6n1iPHjcz6L1SoM-jCpoiyAjBO9W6VLKzA.woff2) format('woff2');
  unicode-range: U+1F00-1FFF;
}
/* greek */
@font-face {
  font-family: 'Noto Sans';
  font-style: normal;
  font-weight: 600;
  font-stretch: 100%;
  font-display: block;
  src: url(https://design.penpot.app/internal/gfonts/font/notosans/v37/o-0mIpQlx3QUlC5A4PNB6Ryti20_6n1iPHjcz6L1SoM-jCpoiyAjBO9Z6VLKzA.woff2) format('woff2');
  unicode-range: U+0370-0377, U+037A-037F, U+0384-038A, U+038C, U+038E-03A1, U+03A3-03FF;
}
/* vietnamese */
@font-face {
  font-family: 'Noto Sans';
  font-style: normal;
  font-weight: 600;
  font-stretch: 100%;
  font-display: block;
  src: url(https://design.penpot.app/internal/gfonts/font/notosans/v37/o-0mIpQlx3QUlC5A4PNB6Ryti20_6n1iPHjcz6L1SoM-jCpoiyAjBO9V6VLKzA.woff2) format('woff2');
  unicode-range: U+0102-0103, U+0110-0111, U+0128-0129, U+0168-0169, U+01A0-01A1, U+01AF-01B0, U+0300-0301, U+0303-0304, U+0308-0309, U+0323, U+0329, U+1EA0-1EF9, U+20AB;
}
/* latin-ext */
@font-face {
  font-family: 'Noto Sans';
  font-style: normal;
  font-weight: 600;
  font-stretch: 100%;
  font-display: block;
  src: url(https://design.penpot.app/internal/gfonts/font/notosans/v37/o-0mIpQlx3QUlC5A4PNB6Ryti20_6n1iPHjcz6L1SoM-jCpoiyAjBO9U6VLKzA.woff2) format('woff2');
  unicode-range: U+0100-02BA, U+02BD-02C5, U+02C7-02CC, U+02CE-02D7, U+02DD-02FF, U+0304, U+0308, U+0329, U+1D00-1DBF, U+1E00-1E9F, U+1EF2-1EFF, U+2020, U+20A0-20AB, U+20AD-20C0, U+2113, U+2C60-2C7F, U+A720-A7FF;
}
/* latin */
@font-face {
  font-family: 'Noto Sans';
  font-style: normal;
  font-weight: 600;
  font-stretch: 100%;
  font-display: block;
  src: url(https://design.penpot.app/internal/gfonts/font/notosans/v37/o-0mIpQlx3QUlC5A4PNB6Ryti20_6n1iPHjcz6L1SoM-jCpoiyAjBO9a6VI.woff2) format('woff2');
  unicode-range: U+0000-00FF, U+0131, U+0152-0153, U+02BB-02BC, U+02C6, U+02DA, U+02DC, U+0304, U+0308, U+0329, U+2000-206F, U+20AC, U+2122, U+2191, U+2193, U+2212, U+2215, U+FEFF, U+FFFD;
}


html, body {
  margin: 0;
  min-height: 100%;
  min-width: 100%;
  padding: 0;
}

body {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100vw;
  min-height: 100vh;
}

* {
  box-sizing: border-box;
}

.text-node { background-clip: text !important; -webkit-background-clip: text !important; }

/* Doctor Management */
.doctor-man-5314659ff9c6 {
  position: relative;
  width: 640px;
  height: 800px;
  background: #FFFFFF;
  overflow: hidden;
  z-index: 0;
}

/* Brand Name */
.brand-name-5315c45e3bdf {
  position: absolute;
  left: 40px;
  top: 370px;
  width: 121px;
  height: 22px;
}
.brand-name-5315c45e3bdf .root-0 {
  
  
  display: flex;
  white-space: break-spaces;
  align-items: flex-start;
}
.brand-name-5315c45e3bdf .root-0-paragraph-set-0 {
  display: inline-flex;
  flex-direction: column;
  justify-content: inherit;
  min-width: 100%;
  margin-right: 1px;
  vertical-align: top;
}
.brand-name-5315c45e3bdf .root-0-paragraph-set-0-paragraph-0 {
  font-size: 0;
  line-height: 1.2;
  margin: 0;
  text-align: left;
}
.brand-name-5315c45e3bdf .root-0-paragraph-set-0-paragraph-0-text-0 {
  color: rgba(0, 0, 0, 1);
  text-transform: none;
  
  line-break: auto;
  overflow-wrap: initial;
  white-space: break-spaces;
  font-size: 18px;
  text-rendering: geometricPrecision;
  caret-color: rgba(0, 0, 0, 1);
  text-decoration: none;
  letter-spacing: 0px;
  font-family: "Space Grotesk";
  font-style: normal;
  font-weight: 600;
}

/* Brand Name */
.brand-name-5315c3beb3d4 {
  position: absolute;
  left: 40px;
  top: 315px;
  width: 121px;
  height: 22px;
}
.brand-name-5315c3beb3d4 .root-0 {
  
  
  display: flex;
  white-space: break-spaces;
  align-items: flex-start;
}
.brand-name-5315c3beb3d4 .root-0-paragraph-set-0 {
  display: inline-flex;
  flex-direction: column;
  justify-content: inherit;
  min-width: 100%;
  margin-right: 1px;
  vertical-align: top;
}
.brand-name-5315c3beb3d4 .root-0-paragraph-set-0-paragraph-0 {
  font-size: 0;
  line-height: 1.2;
  margin: 0;
  text-align: left;
}
.brand-name-5315c3beb3d4 .root-0-paragraph-set-0-paragraph-0-text-0 {
  color: rgba(0, 0, 0, 1);
  text-transform: none;
  
  line-break: auto;
  overflow-wrap: initial;
  white-space: break-spaces;
  font-size: 18px;
  text-rendering: geometricPrecision;
  caret-color: rgba(0, 0, 0, 1);
  text-decoration: none;
  letter-spacing: 0px;
  font-family: "Space Grotesk";
  font-style: normal;
  font-weight: 600;
}

/* Brand Name */
.brand-name-5315c2d6d8f9 {
  position: absolute;
  left: 40px;
  top: 260px;
  width: 121px;
  height: 22px;
}
.brand-name-5315c2d6d8f9 .root-0 {
  
  
  display: flex;
  white-space: break-spaces;
  align-items: flex-start;
}
.brand-name-5315c2d6d8f9 .root-0-paragraph-set-0 {
  display: inline-flex;
  flex-direction: column;
  justify-content: inherit;
  min-width: 100%;
  margin-right: 1px;
  vertical-align: top;
}
.brand-name-5315c2d6d8f9 .root-0-paragraph-set-0-paragraph-0 {
  font-size: 0;
  line-height: 1.2;
  margin: 0;
  text-align: left;
}
.brand-name-5315c2d6d8f9 .root-0-paragraph-set-0-paragraph-0-text-0 {
  color: rgba(0, 0, 0, 1);
  text-transform: none;
  
  line-break: auto;
  overflow-wrap: initial;
  white-space: break-spaces;
  font-size: 18px;
  text-rendering: geometricPrecision;
  caret-color: rgba(0, 0, 0, 1);
  text-decoration: none;
  letter-spacing: 0px;
  font-family: "Space Grotesk";
  font-style: normal;
  font-weight: 600;
}

/* Brand Name */
.brand-name-5315bfa9141a {
  position: absolute;
  left: 40px;
  top: 205px;
  width: 121px;
  height: 22px;
}
.brand-name-5315bfa9141a .root-0 {
  
  
  display: flex;
  white-space: break-spaces;
  align-items: flex-start;
}
.brand-name-5315bfa9141a .root-0-paragraph-set-0 {
  display: inline-flex;
  flex-direction: column;
  justify-content: inherit;
  min-width: 100%;
  margin-right: 1px;
  vertical-align: top;
}
.brand-name-5315bfa9141a .root-0-paragraph-set-0-paragraph-0 {
  font-size: 0;
  line-height: 1.2;
  margin: 0;
  text-align: left;
}
.brand-name-5315bfa9141a .root-0-paragraph-set-0-paragraph-0-text-0 {
  color: rgba(0, 0, 0, 1);
  text-transform: none;
  
  line-break: auto;
  overflow-wrap: initial;
  white-space: break-spaces;
  font-size: 18px;
  text-rendering: geometricPrecision;
  caret-color: rgba(0, 0, 0, 1);
  text-decoration: none;
  letter-spacing: 0px;
  font-family: "Space Grotesk";
  font-style: normal;
  font-weight: 600;
}

/* Brand Name */
.brand-name-5314659ff9cb {
  position: absolute;
  left: 40px;
  top: 150px;
  width: 121px;
  height: 22px;
}
.brand-name-5314659ff9cb .root-0 {
  
  
  display: flex;
  white-space: break-spaces;
  align-items: flex-start;
}
.brand-name-5314659ff9cb .root-0-paragraph-set-0 {
  display: inline-flex;
  flex-direction: column;
  justify-content: inherit;
  min-width: 100%;
  margin-right: 1px;
  vertical-align: top;
}
.brand-name-5314659ff9cb .root-0-paragraph-set-0-paragraph-0 {
  font-size: 0;
  line-height: 1.2;
  margin: 0;
  text-align: left;
}
.brand-name-5314659ff9cb .root-0-paragraph-set-0-paragraph-0-text-0 {
  color: rgba(0, 0, 0, 1);
  text-transform: none;
  
  line-break: auto;
  overflow-wrap: initial;
  white-space: break-spaces;
  font-size: 18px;
  text-rendering: geometricPrecision;
  caret-color: rgba(0, 0, 0, 1);
  text-decoration: none;
  letter-spacing: 0px;
  font-family: "Space Grotesk";
  font-style: normal;
  font-weight: 600;
}

/* Adding Lab Report */
.adding-lab-5314659ff9ca {
  position: absolute;
  left: 350px;
  top: 78px;
  width: 263px;
  height: 29px;
}
.adding-lab-5314659ff9ca .root-0 {
  
  
  display: flex;
  white-space: break-spaces;
  align-items: flex-start;
}
.adding-lab-5314659ff9ca .root-0-paragraph-set-0 {
  display: inline-flex;
  flex-direction: column;
  justify-content: inherit;
  min-width: 100%;
  margin-right: 1px;
  vertical-align: top;
}
.adding-lab-5314659ff9ca .root-0-paragraph-set-0-paragraph-0 {
  font-size: 0;
  line-height: 1.2;
  margin: 0;
  text-align: right;
}
.adding-lab-5314659ff9ca .root-0-paragraph-set-0-paragraph-0-text-0 {
  color: rgba(0, 0, 0, 1);
  text-transform: none;
  
  line-break: auto;
  overflow-wrap: initial;
  white-space: break-spaces;
  font-size: 24px;
  text-rendering: geometricPrecision;
  caret-color: rgba(0, 0, 0, 1);
  text-decoration: none;
  letter-spacing: 0px;
  font-family: "Space Grotesk";
  font-style: normal;
  font-weight: 400;
}

/* MediBASE */
.medi-base-5314659ff9c9 {
  position: absolute;
  left: 40px;
  top: 40px;
  width: 296px;
  height: 77px;
}
.medi-base-5314659ff9c9 .root-0 {
  
  
  display: flex;
  white-space: break-spaces;
  align-items: flex-start;
}
.medi-base-5314659ff9c9 .root-0-paragraph-set-0 {
  display: inline-flex;
  flex-direction: column;
  justify-content: inherit;
  
  margin-right: 1px;
  vertical-align: top;
}
.medi-base-5314659ff9c9 .root-0-paragraph-set-0-paragraph-0 {
  font-size: 0;
  line-height: 1.2;
  margin: 0;
  text-align: left;
}
.medi-base-5314659ff9c9 .root-0-paragraph-set-0-paragraph-0-text-0 {
  color: rgba(64, 21, 32, 1);
  text-transform: none;
  
  line-break: auto;
  overflow-wrap: initial;
  white-space: pre;
  font-size: 64px;
  text-rendering: geometricPrecision;
  caret-color: rgba(64, 21, 32, 1);
  text-decoration: none;
  letter-spacing: -1.5px;
  font-family: "Space Grotesk";
  font-style: normal;
  font-weight: 400;
}
.medi-base-5314659ff9c9 .root-0-paragraph-set-0-paragraph-0-text-1 {
  color: rgba(75, 147, 191, 1);
  text-transform: none;
  
  line-break: auto;
  overflow-wrap: initial;
  white-space: pre;
  font-size: 64px;
  text-rendering: geometricPrecision;
  caret-color: rgba(75, 147, 191, 1);
  text-decoration: none;
  letter-spacing: -1.5px;
  font-family: "Space Grotesk";
  font-style: normal;
  font-weight: 400;
}

/* btn_patients */
.btnpatien-5314659ff9c7 {
  position: absolute;
  left: 203px;
  top: 567px;
  width: 235px;
  height: 45px;
}

/* Create */
.create-531465a039f6 {
  position: absolute;
  left: 41px;
  top: 8px;
  width: 153.68px;
  height: 27px;
}
.create-531465a039f6 .root-0 {
  
  
  display: flex;
  white-space: break-spaces;
  align-items: flex-start;
}
.create-531465a039f6 .root-0-paragraph-set-0 {
  display: inline-flex;
  flex-direction: column;
  justify-content: inherit;
  min-width: 100%;
  margin-right: 1px;
  vertical-align: top;
}
.create-531465a039f6 .root-0-paragraph-set-0-paragraph-0 {
  font-size: 0;
  line-height: 1.5;
  margin: 0;
  text-align: center;
}
.create-531465a039f6 .root-0-paragraph-set-0-paragraph-0-text-0 {
  color: rgba(242, 235, 213, 1);
  text-transform: none;
  
  line-break: auto;
  overflow-wrap: initial;
  white-space: break-spaces;
  font-size: 18px;
  text-rendering: geometricPrecision;
  caret-color: rgba(242, 235, 213, 1);
  text-decoration: none;
  letter-spacing: 0px;
  font-family: "Noto Sans";
  font-style: normal;
  font-weight: 600;
}

/* Group */
.group-5314659ff9d0 {
  position: absolute;
  left: 0px;
  top: 0px;
  width: 235px;
  height: 45px;
}

/* Ellipse */
.ellipse-531465a039f9 {
  position: absolute;
  left: 190.24px;
  top: 0px;
  width: 44.76px;
  height: 45px;
  background: #D92525;
  border-radius: 50%;
}

/* Ellipse */
.ellipse-531465a039f8 {
  position: absolute;
  left: 0px;
  top: 0px;
  width: 44.76px;
  height: 45px;
  background: #D92525;
  border-radius: 50%;
}

/* Rectangle */
.rectangle-531465a039f7 {
  position: absolute;
  left: 22.38px;
  top: 0px;
  width: 190.24px;
  height: 45px;
  background: #D92525;
  border-radius: 0px;
}
    </style>
  <body>
    <form action="result_labreport.jsp" method="post">
      <!-- frame: Doctor Management -->
      <div class="frame doctor-man-5314659ff9c6">
        <!-- group: btn_patients -->
        <div class="group btnpatien-5314659ff9c7">
          <!-- group: Group -->
          <div class="group group-5314659ff9d0">
            <!-- rect: Rectangle -->
            <div class="shape rect rectangle-531465a039f7"></div>
            <!-- circle: Ellipse -->
            <div class="shape circle ellipse-531465a039f8"></div>
            <!-- circle: Ellipse -->
            <div class="shape circle ellipse-531465a039f9"></div>
          </div>
          <!-- text: Create -->
          <div class="shape text create-531465a039f6">
            <div
              class="text-node-html"
              id="html-text-node-8634cca9-2277-8055-8005-531465a039f6"
              data-x="2584"
              data-y="3370"
            >
              <div
                class="root rich-text root-0"
                style="
                  display: flex;
                  white-space: break-spaces;
                  align-items: flex-start;
                "
                xmlns="http://www.w3.org/1999/xhtml"
              >
                <div class="paragraph-set root-0-paragraph-set-0">
                  <p
                    class="paragraph root-0-paragraph-set-0-paragraph-0"
                    dir="ltr"
                  >
                    <input
                      type="submit"
                      id="submit"
                      name="submit"
                      value="Request Lab"
                      class="text-node root-0-paragraph-set-0-paragraph-0-text-0"
                      style="
                        background: none;
                        color: rgba(242, 235, 213, 1);
                        text-transform: none;
                        line-break: auto;
                        overflow-wrap: initial;
                        white-space: break-spaces;
                        font-size: 18px;
                        text-rendering: geometricPrecision;
                        caret-color: rgba(242, 235, 213, 1);
                        text-decoration: none;
                        --font-id: gfont-noto-sans;
                        --fills: [[ '^ ', '~:fill-color', '#F2EBD5',
                          '~:fill-color-ref-id',
                          '~ua19c5e35-ca12-808b-8005-44469bb3a18a',
                          '~:fill-color-ref-file',
                          '~ua5adc15f-fb38-8092-8005-41b26c440392',
                          '~:fill-opacity', 1]];
                        letter-spacing: 0px;
                        font-family: 'Noto Sans';
                        font-style: normal;
                        font-weight: 600;
                      "
                    >
                  </p>
                </div>
              </div>
            </div>
          </div>
        </div>
        <!-- text: MediBASE -->
        <div class="shape text medi-base-5314659ff9c9">
          <div
            class="text-node-html"
            id="html-text-node-8634cca9-2277-8055-8005-5314659ff9c9"
            data-x="2380"
            data-y="2835"
          >
            <div
              class="root rich-text root-0"
              style="
                display: flex;
                white-space: break-spaces;
                align-items: flex-start;
              "
              xmlns="http://www.w3.org/1999/xhtml"
            >
              <div class="paragraph-set root-0-paragraph-set-0">
                <p class="paragraph root-0-paragraph-set-0-paragraph-0" dir="ltr">
                  <span
                    class="text-node root-0-paragraph-set-0-paragraph-0-text-0"
                    style="
                      color: rgba(64, 21, 32, 1);
                      text-transform: none;
                      line-break: auto;
                      overflow-wrap: initial;
                      white-space: pre;
                      font-size: 64px;
                      text-rendering: geometricPrecision;
                      caret-color: rgba(64, 21, 32, 1);
                      text-decoration: none;
                      --font-id: gfont-space-grotesk;
                      --fills: [[ '^ ', '~:fill-color', '#401520',
                        '~:fill-color-ref-id',
                        '~ua19c5e35-ca12-808b-8005-4446f756ba91',
                        '~:fill-color-ref-file',
                        '~ua5adc15f-fb38-8092-8005-41b26c440392', '~:fill-opacity',
                        1]];
                      letter-spacing: -1.5px;
                      font-family: 'Space Grotesk';
                      font-style: normal;
                      font-weight: 400;
                    "
                    >Medi</span
                  ><span
                    class="text-node root-0-paragraph-set-0-paragraph-0-text-1"
                    style="
                      color: rgba(75, 147, 191, 1);
                      text-transform: none;
                      line-break: auto;
                      overflow-wrap: initial;
                      white-space: pre;
                      font-size: 64px;
                      text-rendering: geometricPrecision;
                      caret-color: rgba(75, 147, 191, 1);
                      text-decoration: none;
                      --font-id: gfont-space-grotesk;
                      --fills: [[ '^ ', '~:fill-color', '#4B93BF',
                        '~:fill-color-ref-id',
                        '~ua19c5e35-ca12-808b-8005-44467662b4c0',
                        '~:fill-color-ref-file',
                        '~ua5adc15f-fb38-8092-8005-41b26c440392', '~:fill-opacity',
                        1]];
                      letter-spacing: -1.5px;
                      font-family: 'Space Grotesk';
                      font-style: normal;
                      font-weight: 400;
                    "
                    >BASE</span
                  >
                </p>
              </div>
            </div>
          </div>
        </div>
        <!-- text: Adding Lab Report -->
        <div class="shape text adding-lab-5314659ff9ca">
          <div
            class="text-node-html"
            id="html-text-node-8634cca9-2277-8055-8005-5314659ff9ca"
            data-x="2690"
            data-y="2873"
          >
            <div
              class="root rich-text root-0"
              style="
                display: flex;
                white-space: break-spaces;
                align-items: flex-start;
              "
              xmlns="http://www.w3.org/1999/xhtml"
            >
              <div class="paragraph-set root-0-paragraph-set-0">
                <p class="paragraph root-0-paragraph-set-0-paragraph-0" dir="ltr">
                  <span
                    class="text-node root-0-paragraph-set-0-paragraph-0-text-0"
                    style="
                      color: rgba(0, 0, 0, 1);
                      text-transform: none;
                      line-break: auto;
                      overflow-wrap: initial;
                      white-space: break-spaces;
                      font-size: 24px;
                      text-rendering: geometricPrecision;
                      caret-color: rgba(0, 0, 0, 1);
                      text-decoration: none;
                      --font-id: gfont-space-grotesk;
                      --fills: [[ '^ ', '~:fill-color', '#000000',
                        '~:fill-opacity', 1]];
                      letter-spacing: 0px;
                      font-family: 'Space Grotesk';
                      font-style: normal;
                      font-weight: 400;
                    "
                    >Adding Lab Report</span
                  >
                </p>
              </div>
            </div>
          </div>
        </div>
        <!-- text: Brand Name -->
        <div class="shape text brand-name-5314659ff9cb">
          <div
            class="text-node-html"
            id="html-text-node-8634cca9-2277-8055-8005-5314659ff9cb"
            data-x="2380"
            data-y="2945"
          >
            <div
              class="root rich-text root-0"
              style="
                display: flex;
                white-space: break-spaces;
                align-items: flex-start;
              "
              xmlns="http://www.w3.org/1999/xhtml"
            >
              <div class="paragraph-set root-0-paragraph-set-0">
                <p class="paragraph root-0-paragraph-set-0-paragraph-0" dir="ltr">
                  <input
                  id="labtest1"
                      name="labtest1"
                      type="text"
                      placeholder="Lab Test 1"
                    class="text-node root-0-paragraph-set-0-paragraph-0-text-0"
                    style="
                      color: rgba(0, 0, 0, 1);
                      text-transform: none;
                      line-break: auto;
                      overflow-wrap: initial;
                      white-space: break-spaces;
                      font-size: 18px;
                      text-rendering: geometricPrecision;
                      caret-color: rgba(0, 0, 0, 1);
                      text-decoration: none;
                      --font-id: gfont-space-grotesk;
                      --fills: [[ '^ ', '~:fill-color', '#000000',
                        '~:fill-opacity', 1]];
                      letter-spacing: 0px;
                      font-family: 'Space Grotesk';
                      font-style: normal;
                      font-weight: 600;
                    "
                  >
                </p>
              </div>
            </div>
          </div>
        </div>
        <!-- text: Brand Name -->
        <div class="shape text brand-name-5315bfa9141a">
          <div
            class="text-node-html"
            id="html-text-node-8634cca9-2277-8055-8005-5315bfa9141a"
            data-x="2380"
            data-y="3000"
          >
            <div
              class="root rich-text root-0"
              style="
                display: flex;
                white-space: break-spaces;
                align-items: flex-start;
              "
              xmlns="http://www.w3.org/1999/xhtml"
            >
              <div class="paragraph-set root-0-paragraph-set-0">
                <p class="paragraph root-0-paragraph-set-0-paragraph-0" dir="ltr">
                  <input
                    id="labtest2"
                    name="labtest2"
                    type="text"
                    placeholder="Lab Test 2"
                    class="text-node root-0-paragraph-set-0-paragraph-0-text-0"
                    style="
                      color: rgba(0, 0, 0, 1);
                      text-transform: none;
                      line-break: auto;
                      overflow-wrap: initial;
                      white-space: break-spaces;
                      font-size: 18px;
                      text-rendering: geometricPrecision;
                      caret-color: rgba(0, 0, 0, 1);
                      text-decoration: none;
                      --font-id: gfont-space-grotesk;
                      --fills: [[ '^ ', '~:fill-color', '#000000',
                        '~:fill-opacity', 1]];
                      letter-spacing: 0px;
                      font-family: 'Space Grotesk';
                      font-style: normal;
                      font-weight: 600;
                    "
                  >
                </p>
              </div>
            </div>
          </div>
        </div>
        <!-- text: Brand Name -->
        <div class="shape text brand-name-5315c2d6d8f9">
          <div
            class="text-node-html"
            id="html-text-node-8634cca9-2277-8055-8005-5315c2d6d8f9"
            data-x="2380"
            data-y="3055"
          >
            <div
              class="root rich-text root-0"
              style="
                display: flex;
                white-space: break-spaces;
                align-items: flex-start;
              "
              xmlns="http://www.w3.org/1999/xhtml"
            >
              <div class="paragraph-set root-0-paragraph-set-0">
                <p class="paragraph root-0-paragraph-set-0-paragraph-0" dir="ltr">
                  <input
                    id="labtest3"
                    name="labtest3"
                    type="text"
                    placeholder="Lab Test 3"
                    class="text-node root-0-paragraph-set-0-paragraph-0-text-0"
                    style="
                      color: rgba(0, 0, 0, 1);
                      text-transform: none;
                      line-break: auto;
                      overflow-wrap: initial;
                      white-space: break-spaces;
                      font-size: 18px;
                      text-rendering: geometricPrecision;
                      caret-color: rgba(0, 0, 0, 1);
                      text-decoration: none;
                      --font-id: gfont-space-grotesk;
                      --fills: [[ '^ ', '~:fill-color', '#000000',
                        '~:fill-opacity', 1]];
                      letter-spacing: 0px;
                      font-family: 'Space Grotesk';
                      font-style: normal;
                      font-weight: 600;
                    "
                  >
                </p>
              </div>
            </div>
          </div>
        </div>
        <!-- text: Brand Name -->
        <div class="shape text brand-name-5315c3beb3d4">
          <div
            class="text-node-html"
            id="html-text-node-8634cca9-2277-8055-8005-5315c3beb3d4"
            data-x="2380"
            data-y="3110"
          >
            <div
              class="root rich-text root-0"
              style="
                display: flex;
                white-space: break-spaces;
                align-items: flex-start;
              "
              xmlns="http://www.w3.org/1999/xhtml"
            >
              <div class="paragraph-set root-0-paragraph-set-0">
                <p class="paragraph root-0-paragraph-set-0-paragraph-0" dir="ltr">
                  <input
                    id="labtest4"
                    name="labtest4"
                    type="text"
                    placeholder="Lab Test 4"
                    class="text-node root-0-paragraph-set-0-paragraph-0-text-0"
                    style="
                      color: rgba(0, 0, 0, 1);
                      text-transform: none;
                      line-break: auto;
                      overflow-wrap: initial;
                      white-space: break-spaces;
                      font-size: 18px;
                      text-rendering: geometricPrecision;
                      caret-color: rgba(0, 0, 0, 1);
                      text-decoration: none;
                      --font-id: gfont-space-grotesk;
                      --fills: [[ '^ ', '~:fill-color', '#000000',
                        '~:fill-opacity', 1]];
                      letter-spacing: 0px;
                      font-family: 'Space Grotesk';
                      font-style: normal;
                      font-weight: 600;
                    "
                  >
                </p>
              </div>
            </div>
          </div>
        </div>
        <!-- text: Brand Name -->
        <div class="shape text brand-name-5315c45e3bdf">
          <div
            class="text-node-html"
            id="html-text-node-8634cca9-2277-8055-8005-5315c45e3bdf"
            data-x="2380"
            data-y="3165"
          >
            <div
              class="root rich-text root-0"
              style="
                display: flex;
                white-space: break-spaces;
                align-items: flex-start;
              "
              xmlns="http://www.w3.org/1999/xhtml"
            >
              <div class="paragraph-set root-0-paragraph-set-0">
                <p class="paragraph root-0-paragraph-set-0-paragraph-0" dir="ltr">
                  <input
                    id="labtest5"
                    name="labtest5"
                    type="text"
                    placeholder="Lab Test 5"
                    class="text-node root-0-paragraph-set-0-paragraph-0-text-0"
                    style="
                      color: rgba(0, 0, 0, 1);
                      text-transform: none;
                      line-break: auto;
                      overflow-wrap: initial;
                      white-space: break-spaces;
                      font-size: 18px;
                      text-rendering: geometricPrecision;
                      caret-color: rgba(0, 0, 0, 1);
                      text-decoration: none;
                      --font-id: gfont-space-grotesk;
                      --fills: [[ '^ ', '~:fill-color', '#000000',
                        '~:fill-opacity', 1]];
                      letter-spacing: 0px;
                      font-family: 'Space Grotesk';
                      font-style: normal;
                      font-weight: 600;
                    "
                  >
                </p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </form>
  </body>
</html>