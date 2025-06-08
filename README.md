# SEC-DED Error Detector
Bu proje, Bursa Teknik Üniversitesi Bilgisayar Mimarisi BLM-0230 numaralı dersi kapsamınca hazırlanmıştır.


# Proje sahibi bilgileri
Ad:Yusuf
Soyad:Çil
Öğrenci Numarası:22360859012


## Proje Hakkında
Bu proje, bilgisayarda belleklerde saklanan verilerin zamanla ya da bir takım dış etmenlerle bozulması sonucu belleklerde bulunan bitlerde oluşabilecek tekli ya da çoklu hataları fark eden ve düzelten veya hatalı bit sayısı birden fazlaysa hatayı bulan ve hatalı duruma gelen veriden yola çıkarak Parity bitleri yardımıyla bütün veriyi değiştirerek tekrar kullanılabilir hale getiren bir programdır.


## Çalışma Mantığı
-Kullanıcı programı başlattıktan sonra açılan panele 8/16/32 bitlik veri girişi yapar.
-Hamming Code Algoritması ile girilen veri belleğe kaydedilir.
-Girilen numaralı bitlerde hata oluşturulur.
-Eğer hata sayısı birse sistem hatayı düzeltir, birden fazlaysa hataları kullanıcıya gösterir.

## Nasıl Çalıştırılır?
Projenin kaynak kodunu GitHub'dan indirdikten sonra Java yazılım dilinin çalıştırılabileceği bir IDE yardımıyla programı çalıştırarak deneyimleyebilirsiniz.

## Uygulama İçi Görseller


<h3>Veri giriş ekranı:</h3>
<table>
  <tr>
    <td><img src="resimler/8.png" width="400"></td>
  </tr>
</table>

<h3>8 bitlik veri girişi:</h3>
<table>
  <tr>
    <td><img src="resimler/8.1.png" width="300"></td>
    <td><img src="resimler/8.2.png" width="300"></td>
  </tr>
</table>

<h3>8 bitlik veriye hata eklenmesi:</h3>
<table>
  <tr>
    <td><img src="resimler/8.3.png" width="400"></td>
  </tr>
</table>

<h3>Hatanın düzeltilmesi:</h3>
<table>
  <tr>
    <td><img src="resimler/8.4.png" width="400"></td>
  </tr>
</table>

<h3>16 bitlik veri girişi:</h3>
<table>
  <tr>
    <td><img src="resimler/16.1.png" width="300"></td>
    <td><img src="resimler/16.2.png" width="300"></td>
  </tr>
</table>

<h3>16 bitlik veriye hataların eklenmesi:</h3>
<table>
  <tr>
    <td><img src="resimler/16.3.png" width="400"></td>
  </tr>
</table>

<h3>Hataların düzeltilmesi:</h3>
<table>
  <tr>
    <td><img src="resimler/16.4.png" width="400"></td>
  </tr>
</table>


## Proje Hakkındaki Youtube Videosu Linki
https://youtu.be/Caf_4nhR7mA


