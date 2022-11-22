# Quizkampen
Inlämningsuppgift 4 av Bahareh, John, Jonathan Hellgren och Pontus.

Detta program är en simulering av mobilspelet (Quiz Battle) för studieändamål.
I den här applikationen har vi försökt skapa en enkel funktionalitet för "Hem" och "Klassiskt läge" som i riktiga spel.

Länken för spel:
* [För Android](https://play.google.com/store/apps/details?id=se.maginteractive.quizduel2&hl=sv&gl=US&pli=1)
* [För Mac](https://apps.apple.com/se/app/quizkampen/id1484354626)

### Beskrivning:

Spelet går ut på att man tävlar, två och två per spel, om att svara rätt på quiz-frågor.
Frågorna har olika kategorier och man turas om att välja vilken kategori man ska få frågor
ifrån. Under en omgång får man frågor från en vald kategori, efter varje omgång summeras
poängställningen. Man spelar ett visst antal omgångar innan den totala poängen räknas.
Den spelare som har flest poäng när spelet är slut har vunnit.
## Git workflow:

Få ner koden till datorn (görs bara en gång):
https://blog.jetbrains.com/idea/2020/10/clone-a-project-from-github/

### När ett kort i Trello ska kodas:
1. Skapa en ny branch från master branch:
   https://www.jetbrains.com/help/idea/manage-branches.html
2. Välj att den nya lokala branchen ska vara den aktiva genom att högerklicka på den och välja ”Checkout”.
3. Skriv kod och gör commits.
4. Välj att lokala master ska vara den aktiva genom att högerklicka på den och välja ”Checkout”.
5. Uppdatera lokala master genom att göra en pull från remote master:
   https://www.jetbrains.com/help/idea/sync-with-a-remote-repository.html#pull
7. Gör en merge till lokala master genom att högerklicka på den nya branchen och välja ”merge into current”:
   https://www.jetbrains.com/help/idea/apply-changes-from-one-branch-to-another.html#merge
8. Uppdatera remote master genom att göra en push:
   https://www.jetbrains.com/help/idea/commit-and-push-changes.html#push
9. Kolla på github i webbläsaren att ändringarna syns i koden där.
10. När du är säker på att dina ändringar syns på github i webbläsaren kan du ta bort branchen:
    https://www.jetbrains.com/help/idea/manage-branches.html#delete-branch