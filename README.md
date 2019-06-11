# RLethe
## The test automation framework
Implemented as POM and datadriven along with TestNG.

#### How to use?
Add your Page Objects inside the Object Repository extending the SeleniumActions class. Write the granular actions that needs to be applied on each pages.

For the actions that land on next pages, set the returntype of the specific method to that respective page's class name.

Build your Testcases inside the testcases package.

Do not extend any pageobject classes to the test case classes. Instead create the objects.

