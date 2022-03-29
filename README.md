# 💰Automated Investment advisor/recorder 

## 💹 What it does?

- It gives users investment options to pick and enables them to keep track of how their money is growing over time
- I hope this app helps others to grow interest about finance and seek to educate themselves about money.

More specifically: <br />
By the end of the phase 4, a user can sign up or if they already have an account log in to the app. 
If the user is using the app for the first they will be asked about their risk tolerance and accordingly a few stocks 
are suggested to them. 
After setting up the account users can trade stocks and after each transaction their wallet content is updated and displayed to them.
Each wallet contains all the stocks the user has purchased with associated number of shares and prices.
Users will input the price at which they are buying the stock, by storing that number each time I can calculate their 
overall profit and also display that to them.
Lastly, I want to make a line graph of the users stock growth over time to enhance the user experience.


## Who will use it?

**Anyone** who is seeking for investment strategies and want to compare the progress of different stocks

## 💸 Why is this project of interest to you?

- I like the finance world, especially stock market and usually try to think a head in terms of my financial plans. 
But what if an app can help us to keep track of our investment?
- I have used many finance apps to purchase stocks and always wanted to implement one myself
- Handling users' information is appealing to me!

### As a user I want to:
- Make a profile
- get an investment suggestion based on my risk toleration
- Simulating buying stocks, adding stocks to my profile
- Simulating selling stocks, removing stocks from my profile
- Being able to log back in
- Make sure that anyone who does not have my password cannot log in to my account
- See my wallet content after making any transactions
- Save my username, password and content of my wallet 
- Reload my wallet content, including the stocks I own and their corresponding value
 

##### Phase 4 Task 2:
Print log: <br>
Mon Mar 28 23:07:49 PDT 2022 <br>
buying 1 shares of the vfv stock with the price of 100.0 <br>
Mon Mar 28 23:08:01 PDT 2022 <br>
buying 2 shares of the vbal stock with the price of 35.0 <br>
Mon Mar 28 23:08:19 PDT 2022 <br>
buying 2 shares of the tesla stock with the price of 1000.0 <br>
Mon Mar 28 23:08:28 PDT 2022 <br>
selling 1 vfv with the price of 150.0 <br>
Mon Mar 28 23:08:38 PDT 2022 <br>
unsuccessful attempt to sell -1 tesla with the price of 150.0 <br>
Mon Mar 28 23:08:46 PDT 2022 <br>
selling 1 tesla with the price of 1500.0 <br>
Mon Mar 28 23:08:55 PDT 2022 <br>
unsuccessful attempt to sell 3 vbal with the price of 1500.0 <br>
Mon Mar 28 23:09:02 PDT 2022 <br>
selling 1 vbal with the price of 50.0 

##### Phase 4 Task 3:
I have a main class called Navigator that has a field for all the
panels and keeps as well as Investment, UserProfileAndWallet, and StocksInWallet
It is the entry point of the app that controls all the user interaction and asks for the appropriate functionality
that is introduced in the model package. The most two important of those are buying and selling stocks
by calling the corresponding methods on investment class and changing the stocksInWallet accordingly. <br>
I used hierarchy to abstract the common functions for login and signup panels as well as for buy and sell panels.
This helped with the cohesion of the code and reduced coupling significantly. <br>
I also used the observer pattern so that every time the user buys or sells a stock, I would update the bar chart and wallet
panels correspondingly. <br> <br>
Improvements I would want to make:
- Thinking of ways to reference fewer model classes from navigator; especially not referencing both stocksInWallet and UserProfileAndWallet
- Applying design patterns so that fewer classes have a reference to StocksInWallet class, maybe by applying a huge observer pattern
- Using more sophisticated built-in methods for the selling stocks in investment class and removing its reference to PurchaseStock

