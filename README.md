Application is basically for
tracking my todo and other tasks
1. Splash and Login Activity with password authentication
2. Bottom Navigation bar - 5 tabs
   - Home -> months -> categories with amount -> update those categories amount
   - Dashboard -> total balance, income and expense with pie chart for selected month from spinner
   - Notes -> add note and delete by clicking delete icon
   - Journal -> search them, new activity to write data and update in card view, on long press we can delete
   - Notification -> create task -> new activity - to give task type, date nad icon and provides notification on selected date at 9AM.

3. Multiple Databases are used by using RoomDB libraries with Data class, Dao, RoomDatabase classes.

Updates ::::

18 Jan 2024 ->
1. Added columns in MonthlyCategories - Other Savings & Pay back to Others
2. Fix - Due to case sensitive columns data is not updated in UI, so changed from Personal care -> Personal Care & Home giving -> Home Giving


31 Jan 2024 ->
1. Added columns in MonthlyCategories - Get from Others, Me and Others Expense & Spending on Others
2. update DB version

3. Fix - On MCategoryDB update we are losing the data, included addMigration to persist the data.
4. update MCategory DB to 4
5. Added Extra column in MCategoryDB