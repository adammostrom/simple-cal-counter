# LOG

## 2026-08-01

Jobbat med frontend, kört lite crash course i javascript, försöker hålla js filen minimal och enkel (inget fancy). Frontenden i html har det väsentliga, kan snyggas till lite med lite färger, textrutor med information etc. Kvar på frontend är:
- NutritionBox med resultatvärden samt angivet i gram, antal per sökt gram etc
- övrig nutritional information
- Snygga till stil för att se modern ut och mer "scientific".
- Implementera "CUPS" (1 CUP = 140g) och kör en rough conversion.
- Flytta error meddelande boxen längst ned

Service
- Testing
- Error check
- Cleanup
- Städa upp loggningen, även loggning för errors etc (kom på en bra struktur här), eventuellt lägg till tid för beräkning.
- Sortera/ta bort debug printouts eller ha dem skickade till typ "/dev" eller liknande

Porting/Packaging
- Docker och Docker compose
- Lista ut hur datan ska porteras (databas + content)
- 

## 2026-06-01

Fixat så att fetching från median DB funkar (att fetchDB kollar där först), samt att addToTable funkar för båda tables (table som arg).

La till csv logging för entries som blir discarded, kanske är bra för säkerställning och testning frammåt

## 2026-05-29

Median implemented. Next up is to create store to DB as well as stress test it


## 2026-05-28

Idé, ta prompten från SQL verbatim exakt vad som ges som input och endast det som matchar, tex "rice" så ta bara "rice" och inget "rice with chicken".

Beräkna medianen av alla resultat, returnera (skapa NutritionProduct median) med "median.setName(name + "_median")" och returnera, tryck in i cached samt gör en insert till databasen i ett nytt table "calculated medians". 

Skriv om fetchDB till att först kolla i median_table



## 2026-05-13
Maybe just start with grams, and save DL and CUPS for later for the ENUM, and bother with conversions later

Focus on the data for now:
1. Get the data from the csv (nutrition data) to one file
2. Migrate that data into a postgres db
3. Make the data fetching work

Have "Simple view" and "Detailed view" for the frontend, where detailed is all nutrition data like potassium etc, and simple is just whats relevant for daily intake.
