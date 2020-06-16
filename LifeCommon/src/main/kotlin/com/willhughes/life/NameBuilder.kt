package com.willhughes.life

//        https://forebears.io/earth/surnames and wikipedia for first names
object NameBuilder {

    private var maleFirstNameStr =
        "Peter, Pierre, George, John, Mina, Beshoi, Kirollos, Mark, Fadi, Habib,,Mohamed, Youssef, Ahmed, Mahmoud, Mustafa, Yassin, Taha, Khaled, Hamza, Bilal, Ibrahim, Hassan, Hussein, Karim, Tareq, Abdel-Rahman, Ali, Omar, Halim, Murad, Selim, Abdallah,Santiago,Mateo,Juan,Matías,Nicolás,Benjamín,Pedro,Tomás,Thiago,Santino,José,João,Antônio,Francisco,Carlos,Paulo,Pedro,Lucas,Luiz,Marcos,Wei,Jie,Hao,Yi,Jun,Feng,Yong,Jian,Bin,Lei,Aarav,Reyansh,Mohammad,Vivaan,Ayaan,Vihaan,Atharv,Sai,Advik,Arjun,Ori,David,Ariel,Noam,Eitan,Yosef,Itai,Daniel,Yonatan,Lavi,Sō,Minato,Ichika,Itsuki, Tatsuki,Ren,Hinata,Haruta,Asahi,Haruki,Tomoharu,Sōta,Yuuma,Arata,Ryō,Yūto,Haruto,Haruhito, Sōta,Kanata, Hayato,Taichi,William,Noah,Oscar,Lucas,Victor,Malthe,Oliver,Alfred,Carl,Valdemar,Rasmus,Robin,Oliver,Maksim,Robert,Martin,Kaspar,Oskar,Henri,Markus,Leo,Elias,Oliver,Eino,Väinö,Eeli,Noel,Leevi,Onni,Hugo,Malik,Aputsiaq,Minik,Hans,Inunnguaq,Kristian,Nuka,Salik,Peter,Inuk,Antoni,Jakub,Jan,Szymon,Aleksander,Franciszek,Filip,Mikołaj,Wojciech,Kacper,,Liam,Noah,William,James,Logan,Benjamin,Mason,Elijah,Oliver,Jacob"
    private var femaleFirstNameStr =
        "Shaimaa, Fatma, Maha, Reem, Farida, Aya, Shahd, Ashraqat, Sahar, Fatin, Dalal, Doha, Fajr, Suha, Rowan, Hosniya, Hasnaa, Hosna, Gamila, Gamalat, Habiba,Sofía,María,Lucía,Martina,Catalina,Elena,Emilia,Valentina,Paula,Zoe,Maria,Ana,Francisca,Antônia,Adriana,Juliana,Marcia,Fernanda,Patrícia,Aline,,Mariana,Valentina,Isabella,Sofía,Valeria,María José,Gabriela,Sara,Salomé,Daniela,Ximena,Valentina,María Fernanda,Sofía,María José,Martina,Emilia,Zoe,Mia,Dulce María,Jing,Ying,Yan,Li,Xiaoyan,Xinyi,Jie,Lili,Xiaomei,Tingting,Eden,Yarin,Nur,Sarah,Sillin,Assil,Malk,Maya,Aya,Miyar,,Fatma,Mariam,Hussa,Sherifa,Sara,Reem,Aisha,Dalal,Lulwa,Shaikha,Anna,Hannah,Sophia,Emma,Marie,Lena,Sarah,Sophie,Laura,Mia,Aya,Yasmine,Lina,Sara,Sarah,Sofia,Louise,Nour,Léa,Malak,Aino,Aada,Sofia,Eevi,Olivia,Lilja,Helmi,Ellen,Emilia,Ella,Ivaana,Pipaluk,Nivi,Paninnguaq,Ivalu,Naasunnguaq,Julie,Ane,Isabella,Kimmernaq,Zuzanna,Julia,Lena,Maja,Hanna,Zofia,Amelia,Alicja,Aleksandra,Natalia,Ane,June,Nahia,Irati,Laia,Nora,Izaro,Lucía,Malen,Uxue,Mary,Patricia,Linda,Barbara,Elizabeth,Jennifer,Maria,Susan,Margaret,Dorothy"
    private var lastNameStr =
        "Kumar,Xu,Ali,Zhao,Zhou,Nguyen,Khan,Ma,Lu,Sun,Maung,Zhu,Yu,Kim,Lin,He,Hu,Jiang,Guo,Ahmed,Khatun,Luo,Gao,Akter,Zheng,Tang,Das,Wei,Liang,Islam,Shi,Song,Xie,Han,Mohamed,Garcia,da Silva,Tan,Bai,Deng,Yan,Kaur,Feng,Hernandez,Rodriguez,Cao,Hussain,Hassan,Lopez,Gonzalez,Martinez,Ahmad,Ibrahim,Ceng,Peng,Cai,Tran,Xiao,Pan,Cheng,Yuan,Rahman,Yadav,Perez,Su,I,Le,Fan,Dong,Ram,Tian,Ye,Fu,Hossain,Du,Kumari,Sanchez,Yao,Mohammad,Mohammed,Jin,Pak,Ding,Zhong,dos Santos,Yin,Pereira,Lal,Ren,Liao,Mandal,Cui,Silva,Fang,Sharma,Muhammad,Ferreira,Shah,Begum,Ray,Meng,Qiu,Ramirez,Mondal,Dai,Kang,Patel,Gu,Gomez,Alves,Wen,Pham,Jia,Sah,Xia,Smith,Hong,Diaz,Hasan,Hou,Alam,Xiong,Zou,Prasad,Qin,Ji,Choe,Gong,Rodrigues,Chang,Ghosh,Musa,Uddin,Flores,de Oliveira,Xue,Lei,Santos,Torres,Patil,Diallo,Qi,Cruz,Lai,Gomes,Long,Ramos,de Souza,Fernandez,Hussein,Duan,Ri,Bibi,An,Shaikh,Johnson,Xiang,Morales,Pal,Reyes,Bakhash,Tao,Abdullahi,Gupta,Chong,Kong,Jimenez,Mao,Biswas,Williams,Hoang,Wan,Sahu,Gutierrez,Hao,Abubakar,Saleh,Shao,Saha,Guan,Ruiz,Mo,Abbas,Qian,Roy,Khatoon,Lan,Jones,Oliveira,Sarkar,Castillo,Alvarez,Martin,Brown,Sani,Mendoza,Iqbal,Romero,Rana,Ullah,Qu,Castro,Lee,Ansari,Begam,Yi,Bao,Traore,Sekh,Rojas,Usman,Martins,Moreno,Din,Ortiz,Fernandes,Vu,Malik,Mahato,Ge,Thomas,Paramar,Chu,Ribeiro,Rani,John,Rivera,Phan,Abdullah,Ismail,Mahto,Adamu,Tong,Gonçalves,Vargas,Niu,Xing,Lopes,Osman,Cho,Joseph,Nayak,Pang,Mahmoud,Umar,Rathod,Jadhav,Chan,Coulibaly,Zhan,Bui,Chand,Barman,Bi,Allah,Sato,Mia,Ho,You,Ni,Di,Khaled,Mishra,Herrera,Thakur,Behera,Zhuang,Soares,Costa,Mahamat,Suzuki,Medina,Sultana,Lima,Barbosa,Saeed,Aguilar,Dang,O,Miller,Paswan,Qiao,Muñoz,Dias,Muhammed,Bano,Amin,Chowdhury,Adam,Yusuf,Vo,Ahamad,Wilson,Moussa,Ouedraogo,Ngo,Abdel,Shen,Davis,Nie,Omar,Mendez,Vieira,Majhi,Miranda,Abdi,Ha,Batista,Vazquez,Thakor,Haque,Vasquez,Paul,Chauhan,Mai,Yue,Sardar,Ou,Souza,Huynh,Gan,Pawar,Tu,Shang,Chavez,Rashid,Rai,Pradhan,Naik,Taylor,Do,James,Geng,Karim,Jahan,Hossen,Yun,Khaw,Salazar,Kone,Tanaka,Nawaz,Mi,Mou,Jiao,Guzman,Camara,da Costa,Rao,Ilunga,Zuo,Pinto,Watanabe,Akhter,Issa,Dan,Anderson,Jean,Moreira,Aziz,Takahashi,Hamid,Campos,Shinde,Mendes,Pu,Ngoy,Aliyu,Nath,Khalil,Ivanova,Bello,Suarez,Jackson,Ortega,Said,Ba,Molla,Delgado,Almeida,Truong,Zaman,Garba,Ivanov,Anwar,Thompson,Shu,Ling,Akhtar,Cardoso,Miah,Sin,Molina,Ashraf,Sheikh,Ito,Sari,Rocha,Ke,Dominguez,Chandra,Nunes,Banda,Contreras,Caudhari,Hua,Ei,Salem,Mustafa,Mehmood,Mostafa,Shaik,de Sousa,Juma,Aslam,Raut,Moore,Guerrero,Peter,Machado,Ramadan,Ruan,Soto,Andrade,White,Barry,Acosta,Solomon,Jha,Bala,Ning,Tesfaye,Duong,Lam,Mejia,Yamamoto,Navarro,Ouattara,David,Weng,Teixeira,Nakamura,Mei,Raj,Saad,Simon,Bauri,Araujo,So,Vega,Rios,Alvarado,Bah,Patra,Kamal,Cabrera,Santana,Espinoza,Murmu,Lian,Yılmaz,Duarte,Leon,Sharif,Chi,Solanki,Toure,Kobayashi,Schmidt,Xin,Harris,Pandey,Jing,Sultan,Salah,de Lima,May,Huo,Idris,Müller,Nuñez,Miao,Manuel,Abdallah,Dutta,Pei,Kato,Sheng,Prakash,Sow,Chai,Cauhan,Chon,Im,Saito,de Almeida,Lim,Francisco,Marques,Gonzales,Carvalho,Peña,Aung,Ran,Sawadogo,Perera,Samuel,Sinh,Min,Haruna,Pandit,Hwang,Mu,Ta,Santiago,Ko,Emmanuel,Robinson,Correa,Wong,Aktar,Khin,Walker,Munda,Bashir,Juarez,Chakraborty,Lewis,Franco,Patal,Gul,Hamza,Phiri,Mahmud,Abu,Clark,Jain,Hosen,Chaudhary,Awad,Avila,Diarra,Ndiaye,Pacheco,Abdalla,Keita,Akbar,Chaudhari,Luna,Arias,Pathan,Akther,Habib,Saw,Marquez,Ou yang,Bautista,Charles,Fernando,Abdou,Hoque,Kamble,Suleiman,Cisse,Manjhi,Cha,Young,Husain,King,Mohammadi,Kabir,Fuentes,Marin,Aye,Salman,Monteiro,Sántos,Salim,Dou,Son,Adams,Rehman,Taha,Wright,Hall,Domingos,Kasongo,Bian,Debnath,Sresth,Getachew,Jena,Kaya,Ai,Makavan,Allen,Yoshida,Abdo,Daniel,Thapa,Qasim,Giri,do Nascimento,Teng,Kazem,Yahaya,Mora,Yamada,Sandoval,Jie,Gamal,Scott,Velasquez,Si,Estrada,Roberts,Green,Rivas,Sadiq,Lou,Noor,Escobar,Sousa,Cortes,Isah,Tadesse,Dey,Dinh,Nisha,More,Kamara,Diop,Rasool,Borges,Benitez,Duran,Dao,Abakar,Abebe,Kwon,Akram,Baba,Nascimento,Evans,Mansour,Nong,Girma,Correia,Lawal,Campbell,Siddique,Reddy,Win,Jassim,Gayakwad,Lara,Quispe,Sinha,de La Cruz,Kuang,Valencia,Isa,Shaw,Hill,Guerra,Bux,Yakubu,Nelson,Maldonado,Pierre,Aguirre,Vasav,Calderon,Ahmadi,Vera,Valdez,Karmakar,Aminu,Koffi,Baker,Varma,Trinh,George,Serrano,Mun,Xi,Cardenas,Demir,Saleem,Jian,Haider,Sosa,Arif,Kouassi,Che,Jana,Omer,Lang,Schneider,Bag,de Jesus,Meyer,Mohsen,Abdul,Ly,Parvin,Magar,Sylla,Figueroa,Adel,Villanueva,Padilla,Hadi,de Carvalho,Ju,Ayala,Sih,Michael,Pineda,Rosales,Hayat,Edwards,Quan,Zin,Mitchell,De,Rahim,Blanco,Hosseini,Kadam,Haji,Sasaki,Barik,Brito,da Conceiçao,Oraon,Dembele,Carrillo,San,de Araujo,Babu,Khalaf,Tudu,Rong,Al Numan,Rahaman,Matsumoto,Velazquez,Sunday,Setiawan,Adhikari,Mal,Gil,Camacho,Nasser,Morris,Tiwari,Khine,Elias,Tavares,Eze,Bhagat,Yousef,Phillips,Yamaguchi,Imran,Mahmood,Sarker,Hamed,Latif,Turner,Bezerra,Murphy,Mali,Afzal,Salisu,Swain,Kebede,Mamani,Pinheiro,Ng,Konate,Mahdi,Yousuf,Mori,Sing,Solis,Zhuo,Jan,Carter,Bahadur,Stewart,Kouadio,Barros,Inoue,António,Bravo,Mallik,Salas,Khalid,Anh,Parra,Kelly,Paek,Sidibe,Ti,Chin,Sekha,Kanwar,Kouame,Nair,Verma,Collins,Quintero,Bekele,Bo,Cooper,Wagner,Samir,Rasheed,Çelik,Akpan,Weber,dela Cruz,Şahin,Nahar,Sheik,Dube,Fofana,Phyo,Parker,Morgan,Leng,Van,Pires,Pedro,Hnin,Cortez,Luong,Nam,Shehu,Yıldız,Câmara,Kimura,Aparecido,Palacios,Abe,Ahamed,Gabriel,Balde,Doan,Petrov,Coelho,Joshi,Na,Lucas,Yıldırım,Mane,Hidayat,Barrios,Bera,Amadi,Nabi,Bell,Miya,Sen,Dutt,Oumarou,Shankar,Basumatary,Robles,Fischer,Dei,Sayed,Hailu,Kouakou,Soliman,Hamad,Wood,Hansen,Mir,Alemayehu,Espinosa,Peralta,Leite,Dlamini,Hayashi,Salam,Wati,Shimizu,Fonseca,Miguel,Meza,Lestari,Melo,Freitas,Panda,Jang,Watson,Leal,Sahani,Saidi,Ward,Halder,Mousa,Moyo,Ochoa,Lozano,Paredes,Cook,Hughes,Otieno,Farah,Osorio,Bhoi,Guevara,Shafi,Cen,Rogers,Adoum,Than,Nogueira,Alonso,Öztürk,Abbasi,Sulaiman,Thin,Sangma,Salinas,Ao,Lau,Valenzuela,Thanh,Gou,Raza,Nasir,Mensah,Gogoi,Petrova,Saputra,Pramanik,Zapata,Adamou,Roman,Fei,Sui,Mukherjee,Sha,Kamel,Aydın,Man,Vaghel,Manna,Rossi,Conde,Abba,Dad,Banza,Amadou,Ndlovu,Ponce,Neto,Orozco,Salih,Banerjee,Bailey,Zamora,Sahoo,Yahya,Karimi,Kuznetsova,Reis,Kale,Yassin,Hameed,Ono,Aquino,Moses,Taman,Bagdi,Matos,Neves,Jacob,Rathav,Younis,Ramzan,Soe,Narayan,Khalifa,Ibarra"

    private var maleFirstList = ArrayList<String>()
    private var femaleFirstList = ArrayList<String>()
    private var lastList = ArrayList<String>()

    init {
        maleFirstList = maleFirstNameStr.split(",").map { it.trim() } as ArrayList<String>
        femaleFirstList = femaleFirstNameStr.split(",").map { it.trim() } as ArrayList<String>
        lastList = lastNameStr.split(",").map { it.trim() } as ArrayList<String>
    }

    fun uniqueName(gender: Gender, fathersName: String?): Person.Name {
        var lastName = lastList.random()
        if (fathersName != null) lastName = fathersName
        if (gender == Gender.MALE) return Person.Name(maleFirstList.random(), lastName)
        return Person.Name(femaleFirstList.random(), lastName)
    }
}