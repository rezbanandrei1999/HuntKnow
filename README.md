HUNT & KNOW

Fiecare user incepe cu un test de 5..10 intrebari cultura generala. In functie de numarul de intrebari corecte, 
el primeste o locatie random dupa un anumit timp unde va gasi urmatorul set de intrebari. Timpul e bazat pe scor,
 iar fiecare test poate fi facut o singura data. Locatiile pot fi separate pe domenii sau dificultate, userul fiind 
nevoit sa treaca prin toate inainte de locatia de final, comuna pentru toti userii.

1. Pagina de Login va contine: - login cu user si parola, sau seria telefonului
2. Pagina de Home ( redirectionare dupa Login ) va contine: - Imagine de fundal - buton de scan ( enable dupa un anumit timp ) - buton pentru view locations - buton de leaderboard
3. Pagina de Scan va contine un cititor de cod QR
4. Pagina de Locations va contine checkpoint-urile vizitate ( verde ) si urmatorul checkpoint ( rosu )
5. Pagina de leaderboard va contine clasamentul echipelor ce joaca    
   
   User:
6. Admin - automatizat - porneste jocul - trimite locatii in functie de userii normali - trimite mesaj castigatorului
7. Normal - citeste cod qr - raspunde la intrebari - verifica leaderboard
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".ScanActivity">
    <SurfaceView
        android:id="@+id/surfaceQRScanner"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />
</androidx.constraintlayout.widget.ConstraintLayout>