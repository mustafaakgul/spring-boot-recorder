package com.milatcam.site.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping(value = {"/", "/index",})
    public String getindex(){

        return "index";
    }

    @RequestMapping(value = {"/iletisim"})
    public String getcontactUs(){

        return "iletisim";
    }

    @RequestMapping(value = {"/referanslar"})
    public String getreferences(){

        return "referanslar";
    }

    @RequestMapping(value = {"/faaliyetalan"})
    public String getfaaliyetAlan(){

        return "faaliyetalan";
    }

    @RequestMapping(value = {"/profil"})
    public String getprofil(){

        return "profil";
    }

    @RequestMapping(value = {"/vizyonmisyon"})
    public String getvizyonmisyon(){

        return "vizyonmisyon";
    }

    @RequestMapping(value = {"/galeri"})
    public String getgaleri(){

        return "galeri";
    }

    @RequestMapping(value = {"/politikalar"})
    public String getpolitika(){

        return "politikalar";
    }

    @RequestMapping(value = {"/urunler"})
    public String geturunler(){

        return "urunler";
    }
}
