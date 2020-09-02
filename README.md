# hackathon

Design microservices for following use cases

Balance Inquiry 
- Account Type
- Account Number 
- Unique Message ID 

2. Fund Transfer ( Same Bank ) 
- Account Type
- Debit Account Number 
- Credit Account Number 
- Unique Message ID 
- Transaction Amount 

3. Credit Transfer ( Different Bank ) 
- Account Type
- BSB Code ( BSB validation logic to be added )
- Debit Account Number 
- Credit Account Number ( To arrive at basis sender Bank Code. Bank code and account number to be maintained in local DB ) 
- Unique Message ID 
- Transaction Amount 
