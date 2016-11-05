**Model and Method Selection**

	In keeping with a formal developmental process, the work to be undertaken from this point on will need to be fit around an engineering **model **and carried forward by a supplementary **methodology**. These two terms are often used interchangeably, but they actually define entirely different things: whereas a model can set "the order of the stages involved in software development" [citation] and the overall criteria for moving between those stages [citation], a method will instead detail exactly how a team can navigate through those stages and meet the aforementioned criteria [citation]. This carries the obvious connotation that developmental methods can only be fit around engineering models, so it was understandably necessary to take a decision on an appropriate model before implementing and adapting a suitable methodology around the rest of the project.

	Research was made into a variety of models - including the waterfall, spiral, V-model and evolutionary models, amongst others - and considerations were made on which of them would fit most optimally around the project’s scope and the upcoming commitments of the development team’s members. Unsurprisingly, each model had their own appreciable benefits: the waterfall model wouldn’t have required a great deal of planning to implement because of how linear it is, whereas the evolutionary and spiral models cater quite well to changes in customers’ requirements on account of their iterative components. However, in spite of their advantages, it was ultimately agreed that all of these traditional models were simply too rigid for a team of our size, experience and physical proximity to take up without compromising heavily on working efficiency.

The project’s requirements **are **going to be altered as it goes on - there’s no doubt about that, given that many game-design decisions are typically made after stakeholders test prototypes - and being unable to respond to directional changes because of some rigorous engineering model’s arbitrary guidelines would severely impact upon the likelihood for a working, well-design game to be delivered within the project’s strict time-constraints. Furthermore; while documentation will be needed to create reference points from which further work and decisions can be derived, most traditional models call for particularly excessive amounts of documentation to be written up (especially about concepts and designs that end up having absolutely no developmental bearing whatsoever) and it simply wouldn’t be productive to write more than what is truly necessary to suitably inform all pending design decisions.

[FIGURE HERE]

	Then, there’s the fact that the project concerns the development of a game: a type of product that, unlike a strictly functional application, can only be assessed subjectively and will differ in perceived "quality" from person-to-person. The game that comes out of this project will - in effect - only be as good as what the project’s primary stakeholder deems it to be, so a significant part of the work that the project carries will basically be dictated by that stakeholder. Traditional models, however, limit stakeholders’ influences during design and implementation phases: hence, if one was to be adopted for this project, the potential for our primary stakeholder’s requests to be ignored and for precious time to be wasted on implementing unrequested features instead would be drastically raised.

	Rather than fitting the rest of the project around a traditional model, then, it would be far more suitable to direct it ourselves by a set of principles with the aim of making it feel as efficient and as flexible as possible to get through. Hence, the next stage of the project will be fit around the **agile **philosophy, which is described in the figure that follows. This is a direct response to the traditional issues of software development that were raised in the last two paragraphs - as is exhibited by the "agile manifesto" - and along with enabling all of us to direct and work on the project in any manner that we or our primary stakeholder see fit, it should leave the door open for another team to pick the project up and run with it in whatever manner they may choose too.

<table>
  <tr>
    <td>AGILE MODEL FOR SOFTWARE DEVELOPMENT</td>
    <td></td>
  </tr>
  <tr>
    <td>Manifesto
Individuals and interactions over processes and tools
This refers to adapting projects around frequent communications and decisions rather than arbitrary rigors
Working software over comprehensive documentation
Customer collaboration over contract negotiation
Responding to change over following a plan</td>
    <td>Justification
It basically encourages teams to drop formalities and do what they think would be the best for their respective projects…
...and we can do this sensibly because we’re able to communicate frequently, both in text and in person
We don’t want to restrict ourselves to specific roles and/or disciplines when some tasks may take priority over others at certain times
The chances of our requirements changing over time is quite high, and we’d like to adapt to such changes without having to waste so much prior work and/or documentation
The project’s limited time-frame necessitates delivering a working game over writing excessive documentation
We don’t want to dictate the development practices that our successors will have to follow</td>
  </tr>
</table>


* * *


	Once an agile model was chosen for the project’s procedure, a methodology needed to be fit around it. Once again, numerous methodologies - including the extreme programming, dynamic systems development, feature-driven development and agile unified process methods - were considered, and each had their appreciable advantages: XP’s focus on responding to user stories is suitably client-centric enough to satisfy our obligations to the project’s primary stakeholder, whereas the DSD and FDD methods each encourage prototyping and testing to such an extent that could potentially allow for the project’s requirements to be finalised quite early on. In the end, though, the **scrum **methodology was chosen for how well it can leverage frequent communication for productive benefit and for how it could enable the rest of the project to be fit around the team’s shared university schedule and individual commitments.

<table>
  <tr>
    <td>SCRUM METHODOLOGY FOR AGILE MODEL</td>
    <td></td>
    <td></td>
    <td></td>
  </tr>
  <tr>
    <td>Description</td>
    <td>Advantages</td>
    <td>Disadvantages</td>
    <td>Justification</td>
  </tr>
</table>


* * *


<table>
  <tr>
    <td>LIST OF PROJECT RESOURCES</td>
    <td></td>
    <td></td>
  </tr>
  <tr>
    <td>Task</td>
    <td>Product</td>
    <td>Use(s) in Project</td>
  </tr>
  <tr>
    <td>Version Control</td>
    <td>GitHub</td>
    <td>Allows coding work to be reverted if stakeholder requirements change; prevents unauthorised changes to work (and requires the entire team to approve of any changes to the game’s master copy); facilitates prototyping through branching</td>
  </tr>
  <tr>
    <td>File-Sharing</td>
    <td></td>
    <td>Repository acts as an online source for implementation work, enabling the entire team to access one-another’s contributions flexibly and independently</td>
  </tr>
  <tr>
    <td></td>
    <td>Google Drive</td>
    <td>Acts as an online source for all non-implementation work and resources, including documentation and meeting records</td>
  </tr>
  <tr>
    <td>Documentation</td>
    <td></td>
    <td>Includes a web-hosted word-processor through which documentation can be accessed and edited collaboratively</td>
  </tr>
  <tr>
    <td>Burn-Down Analysis</td>
    <td>ZenHub</td>
    <td>Automatically measures task completions over sprints’ durations and uses them to generate "burndown charts" showing whether or not sprints are on-track</td>
  </tr>
  <tr>
    <td>Task Management</td>
    <td></td>
    <td>Allows task-lists and sprint-lists to be logged directly within our chosen VCS and our game’s repository, providing flexible access to those backlogs and assisting in assigning tasks to particular team members</td>
  </tr>
  <tr>
    <td>Communication</td>
    <td>Slack</td>
    <td>Provides a reliable way for team members to remain in contact by maintaining a private online chat-room for the team to access at any time</td>
  </tr>
  <tr>
    <td>UML Modelling</td>
    <td>LucidChart</td>
    <td>Partially automates the creation of UML diagrams and use-case diagrams, which will be required to describe our game’s internal architecture and how the game’s players will ideally interact with it</td>
  </tr>
  <tr>
    <td>Testing</td>
    <td>Travis CI</td>
    <td>Augments our chosen VCS with external servers on which new commits can be tested prior to being pulled (through an online terminal), preventing the need for the files changed in such commits to be downloaded, compiled and tested manually instead</td>
  </tr>
</table>


Test-driven development

Continuous integration

SMARTSHEET

