# LOG

## 2026-06-01

Fixat så att fetching från median DB funkar (att fetchDB kollar där först), samt att addToTable funkar för båda tables (table som arg).

La till csv logging för entries som blir discarded, kanske är bra för säkerställning och testning frammåt

## 2026-05-29

Median implemented. Next up is to create store to DB as well as stress test it


## 2026-05-28

Idé, ta prompten från SQL verbatim exakt vad som ges som input och endast det som matchar, tex "rice" så ta bara "rice" och inget "rice with chicken".

Beräkna medianen av alla resultat, returnera (skapa NutritionProduct median) med "median.setName(name + "_median")" och returnera, tryck in i cached samt gör en insert till databasen i ett nytt table "calculated medians". 

Skriv om fetchDB till att först kolla i median_table



