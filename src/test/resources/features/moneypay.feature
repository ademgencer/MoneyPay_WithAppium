Feature: Applikasyon da Giriş Yap butonu işlevselliği kontrol edilir.

  Scenario: Kullanici uygulamaya girer ve giriş yap butonuna tiklar
    Given Kullanici uygulamaya giriş yapar
    When  "Giriş Yap" butonuna tiklar
    Then Telefon numarasi input alani gormelidir
    When Geçerli fakat yanlis bir telefon numarasi girer ve "Giriş Yap" butonuna tiklar
    Then Geçersiz giris olduguna dair uyari yazisini görmeli
