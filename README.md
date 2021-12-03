# ISHA-R.-ZendeskCodingChallenge
Submission for Zendesk Summer 2022 Internship Coding Challenge



In order to install:
Download project zip file and, as the language is Java (SE-11), ensure that dependencies and libraries used by the 3 src class files are available. 
I personally added (https://github.com/stleary/JSON-java) stleary's most recent jar file into my classpath for org.json imports to format my string into a json array.

The APIConnect source file performs the main GET request to the Zendesk Ticket API in order to retrieve a list of all tickets. I used HttpURLConnection to 
retrieve a string of output, which is then formatted into a JSON Array in the getOverallTickets() method.

The TicketDriver Class uses the APIConnect connection to the user's account to retrieve user details in a user-friendly manner using Scanner input and log 
into their account in order to present their tickets in lists of at most 25 tickets.

The TicketTester class performs some basic tests on the APIConnect class to ensure that, at a level that I am familiar with on my own account, the retrieval 
of tickets is functional.

Thanks!
