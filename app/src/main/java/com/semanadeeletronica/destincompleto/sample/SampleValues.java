package com.semanadeeletronica.destincompleto.sample;


import com.semanadeeletronica.destincompleto.model.Destination;

import java.util.ArrayList;

/**
 * Created by Aline Borges on 11/9/2014.
 */
public class SampleValues {

    public static ArrayList<Destination> getDestinationsList() {
        ArrayList<Destination> destinationList = new ArrayList<Destination>();

        for (int i = 0 ; i < images.length ; i++) {
            Destination d = new Destination();
            d.setImage_url(images[i]);
            d.setNome(nome[i]);
            d.setCuriosidade(curiosidade[i]);
            d.setRegiao(regiao[i]);
            d.setPais(pais[i]);
            destinationList.add(d);
        }

        return destinationList;
    }

    private static String[] images = {
            "http://www.ladyelliot-island.com/index/aerial-485.jpg",
            "http://vacationadvice101.com/wp-content/uploads/2013/06/hammerfest-northern-lights.jpg",
            "http://siamadventureworld.com/wp/wp-content/uploads/2013/08/30.jpg",
            "http://www.wallpaperup.com/uploads/wallpapers/2013/03/06/48646/30b6c1f9efca3fa2ffe443a2cd9f9fe9.jpg",
            "http://global-conferences.eu/wp-content/uploads/2013/08/3.jpg",
            "http://www.trippatravel.co.nz/files/userfiles/images/Fiordland-National-Park.jpg",
            "http://wanderingtrader.com/wp-content/uploads/2012/08/Lagoa-Paraiso-near-Jericoacoara-Brazil.jpg",
            "http://upload.wikimedia.org/wikipedia/commons/1/1e/153_-_Glacier_Perito_Moreno_-_Grotte_glaciaire_-_Janvier_2010.jpg",
            "http://www.travelnics.com/wp-content/uploads/2014/02/Whitehaven-Beach7.jpg",
            "http://risewall.com/wp-content/uploads/2014/08/Lake-Louise-Canada-background-Wallpaper.jpg"
    };

    private static String[] nome = {
            "Lady Elliot Island",
            "Hammerfest",
            "Phi Phi Island",
            "Yosemite",
            "Burj Khalifa",
            "Fjordlands",
            "Jericoacoara",
            "Perito Moreno Glacier",
            "Whitsundays Islands",
            "Lake Louise"
    };

    private static String[] curiosidade = {
            "Foi considerada assombrada até certo tempo pelos pássaros de hábitos noturnos que fazem barulhos fantasmagóricos",
            "Um ótimo lugar para observar a Aurora Boreal",
            "Famoso depois do filme de Leonardo di Caprio, \"The beach\", foi devastada por um tsunami em 2014",
            "Possui regiões de sequoias gigantes que chegam a 90 metros de altura, algumas com mais de 3 mil anos de idade. Existe uma com um túnel no tronco com espaço suficiente para um carro",
            "Atualmente o maior prédio do mundo, foi construído para quebrar recordes, e possui apartamentos tanto residenciais quanto comerciais.",
            "Um de seus fjordes, Milford Sound, é considerado um dos lugares com maior quantidade de chuva do mundo, com uma média anual de 6.813mm (em Curitiba, esta média é de cerca de 1.400mm). Este fenômeno cria dezenas de cachoeiras temporárias nas montanhas que cercam os Fjordes",
            "\"Jericoaquara\" é um termo da língua tupi e significa \"toca das tartarugas-marinhas\", por meio da junção dos termos îurukûá (tartaruga-marinha) e kûara (toca)",
            "Com cerca de 250lm² de gelo, é o terceiro maior reservatório de água doce da terra",
            "Uma das ilhas abriga a famosa \"Whitehaven beach\", considerada a terceira praia mais bonita do mundo. Suas areais são 90% pura sílica e não ficam quentes nem mesmo em dias de extremo calor",
            "Tem clima considerado subártico, com temperaturas recordes de -50 graus celsius e neve média de 3.3m por ano"
    };

    private static String[] regiao = {
            "Grande Barreira de Corais",
            "Finnmark",
            "Krabi",
            "California",
            "Dubai",
            "Ilha do Sul",
            "Nordeste",
            "Patagonia",
            "Queensland",
            "Alberta"
    };

    private static String [] pais = {
            "Austrália",
            "Noruega",
            "Tailândia",
            "Estados Unidos",
            "Emirados Árabes",
            "Nova Zelândia",
            "Brasil",
            "Argentina",
            "Austrália",
            "Canada"
    };


}
