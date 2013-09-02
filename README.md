Isopharma: Finding similar medicines conveniently
==================================================

*A solution to expensive medicines*

Little do people know that there exists multiple medicines in pharmacy that can be used
for a particular treatment. Isopharma primarily aims to create an awareness among the
citizens about the existence of such similar medicines that only differ by name.

### What is Isopharma?

Isopharma is a web-app that helps the users find the other medicines that exist by a 
different name but can be used for a particular treatment. The medicines that exist by
different names but used for same purpose are determined by it's therapeutic value.
Isopharma makes use of the therapeutic value to group different medicines for a cure.

In layman's term, Brand name is the name incorporated by the medicine producing units
for sale whereas Medical name is the name identified by the medicine producing units
based on the treatment it will be used for.

Eg: Medicine with brand name A has medical name as S
    Medicine with brand name B has medical name as S
    
    We can agree that medicine A can also be used by patients who are prescribed to use
    medicine B and vice-versa.

So when a user searches for medicine A, the results will also display medicine B as
the medicine with same therapeutic value.

### How Isopharma works?

Isopharma makes use of MySQL database to retrieve data. All the matching results are 
found based on the medicine name of the brand name entered in the search query.
The database has also stored the prices of the medicine. These medicines are linearly
displayed in the increasing order of their price.

### Potential applications of Isopharma

Isopharma can be used by patients who cannot afford expensive medicines. It is also 
recommended that the patients get the authorization from their doctors when they opt
for cheaper medicines. It is because the composition of the suggested medicine may be
allergic to their body. 

New employees in pharmacy can suggest medicines to their customers based on the results
if the medicine requested by the customer is unavailable.

### Correctness of data

The similar medicines suggested by Isopharma is based on the pdf of medicines and their
therapeutic equivalence provided by the Government of India.
