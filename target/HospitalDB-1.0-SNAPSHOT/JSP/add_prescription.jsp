<%-- 
    Document   : add_prescription
    Created on : Nov 18, 2024, 2:14:52 PM
    Author     : Windows User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.text.ParseException" %>
<%@page import="java.util.*" %>
<%@ page import="com.source.HospitalDB.App" %>


<!DOCTYPE html>
<html>
  <head>
    <title>MediBASE: New Prescription</title>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="style/new_prescription.css" />
  </head>
  <body>
    <!-- frame: Add Prescription -->
    <form action="JSP/result_prescription.jsp" method="post">
        <div class="frame add-prescr-4ab9dc83e311">
            <!-- group: btn_patients -->
            <div class="group btnpatien-4ab9dc83e312">
            <!-- group: Group -->
            <div class="group group-4ab9dc83e31d">
                <!-- rect: Rectangle -->
                <div class="shape rect rectangle-4ab9dc83e31f"></div>
                <!-- circle: Ellipse -->
                <div class="shape circle ellipse-4ab9dc83e320"></div>
                <!-- circle: Ellipse -->
                <div class="shape circle ellipse-4ab9dc83e321"></div>
            </div>
            <!-- text: Prescribe -->
            <div class="shape text prescribe-4ab9dc83e31e">
                <div
                class="text-node-html"
                id="html-text-node-339bc288-5112-8014-8005-4ab9dc83e31e"
                data-x="1892"
                data-y="2419"
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
                        <a
                        href="JSP/add_prescription_result.jsp"
                        class="text-node root-0-paragraph-set-0-paragraph-0-text-0"
                        style="
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
                            '~ua5adc15f-fb38-8092-8005-41b26c440392', '~:fill-opacity',
                            1]];
                            letter-spacing: 0px;
                            font-family: 'Noto Sans';
                            font-style: normal;
                            font-weight: 600;
                        "
                        >Prescribe</a
                        >
                    </p>
                    </div>
                </div>
                </div>
            </div>
            </div>
            <!-- text: MediBASE -->
            <div class="shape text medi-base-4ab9dc83e314">
            <div
                class="text-node-html"
                id="html-text-node-339bc288-5112-8014-8005-4ab9dc83e314"
                data-x="1691"
                data-y="1949"
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
                    <a
                        href="index.html"
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
                        >Medi</a
                    ><a
                        href="index.html"
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
                        >BASE</a
                    >
                    </p>
                </div>
                </div>
            </div>
            </div>
            <!-- text: Adding a Prescription -->
            <div class="shape text adding-a-p-4ab9dc83e315">
            <div
                class="text-node-html"
                id="html-text-node-339bc288-5112-8014-8005-4ab9dc83e315"
                data-x="2001"
                data-y="1987"
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
                        --fills: [[ '^ ', '~:fill-color', '#000000', '~:fill-opacity',
                            1]];
                        letter-spacing: 0px;
                        font-family: 'Space Grotesk';
                        font-style: normal;
                        font-weight: 400;
                        "
                        >Adding a Prescription</span
                    >
                    </p>
                </div>
                </div>
            </div>
            </div>
            <!-- text: Brand Name: -->
            <div class="shape text brand-name-4ab9dc83e316">
            <div
                class="text-node-html"
                id="html-text-node-339bc288-5112-8014-8005-4ab9dc83e316"
                data-x="1691"
                data-y="2059"
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
                        type="text"
                        id="brand_name"
                        name="brand_name"
                        placeholder="Brand Name"
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
                        --fills: [[ '^ ', '~:fill-color', '#000000', '~:fill-opacity',
                            1]];
                        letter-spacing: 0px;
                        font-family: 'Space Grotesk';
                        font-style: normal;
                        font-weight: 600;
                        "
                    />
                    </p>
                </div>
                </div>
            </div>
            </div>
            <!-- text: Generic Name -->
            <div class="shape text generic-na-4ab9dc83e317">
            <div
                class="text-node-html"
                id="html-text-node-339bc288-5112-8014-8005-4ab9dc83e317"
                data-x="1691"
                data-y="2111"
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
                        type="text"
                        id="generic_name"
                        name="generic_name"
                        placeholder="Generic Name"
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
                        --fills: [[ '^ ', '~:fill-color', '#000000', '~:fill-opacity',
                            1]];
                        letter-spacing: 0px;
                        font-family: 'Space Grotesk';
                        font-style: normal;
                        font-weight: 600;
                        "
                    />
                    </p>
                </div>
                </div>
            </div>
            </div>
            <!-- text: Frequency -->
            <div class="shape text frequency-4ab9dc83e318">
            <div
                class="text-node-html"
                id="html-text-node-339bc288-5112-8014-8005-4ab9dc83e318"
                data-x="1691"
                data-y="2163"
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
                        type="number"
                        id="frequency"
                        name="frequency"
                        placeholder="Frequency"
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
                        --fills: [[ '^ ', '~:fill-color', '#000000', '~:fill-opacity',
                            1]];
                        letter-spacing: 0px;
                        font-family: 'Space Grotesk';
                        font-style: normal;
                        font-weight: 600;
                        "
                    />
                    </p>
                </div>
                </div>
            </div>
            </div>
            <!-- text: Dosage -->
            <div class="shape text dosage-4ab9dc83e319">
            <div
                class="text-node-html"
                id="html-text-node-339bc288-5112-8014-8005-4ab9dc83e319"
                data-x="1691"
                data-y="2215"
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
                        type="number"
                        name="dosage"
                        id="dosage"
                        placeholder="Dosage in grams"
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
                        --fills: [[ '^ ', '~:fill-color', '#000000', '~:fill-opacity',
                            1]];
                        letter-spacing: 0px;
                        font-family: 'Space Grotesk';
                        font-style: normal;
                        font-weight: 600;
                        "
                    />
                    </p>
                </div>
                </div>
            </div>
            </div>
        </div>
    </form>
    <% 
    int consult_id = Integer.parseInt(request.getParameter("consult_id"));
    %>
  </body>
</html>

