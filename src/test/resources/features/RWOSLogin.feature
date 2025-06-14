Feature: Login Page RWOS

Scenario Outline: Login to RWOS for order processing

Given User is on login page
When User enters "<UserName>" and "<Password>"
And Click on Sign In button
Then User is navigated to RWOS SIT Dashboard

Examples:
|Iteration|UserName			|Password		 |		
|1				|mukesh.mishra|Mukesh@54321|