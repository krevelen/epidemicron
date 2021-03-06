/* $Id$
 * 
 * Part of ZonMW project no. 50-53000-98-156
 * 
 * @license
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy
 * of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 * 
 * Copyright (c) 2016 RIVM National Institute for Health and Environment 
 */
package nl.rivm.cib.episim.pilot;

import nl.rivm.cib.episim.model.disease.infection.Pathogen;

/**
 * {@link Measles} (rubeola, morbilli, mazelen) is a highly contagious and
 * potentially deadly disease caused by the measles virus (MeV) including
 * respiratory system infections and a generalized rash. It is described at
 * <a href="https://en.wikipedia.org/wiki/Measles">wikipedia</a> and has a
 * <a href="http://rijksvaccinatieprogramma.nl/De_ziekten/Mazelen">RVP page</a>,
 * a <a href="http://www.rivm.nl/Onderwerpen/M/Mazelen">RIVM page</a>, a
 * <a href="http://www.cdc.gov/measles/">US CDC page</a>, and a
 * <a href="http://www.who.int/topics/measles/en/">WHO page</a>
 * <ul>
 * measles time line:
 * <li>incubation period: 9-12d (Principles of Virology, 2008) or 10-14d
 * (<a href="https://en.wikipedia.org/wiki/File:Measles.webm">Wikipedia</a>) or
 * (mean) generation time <em>u</em>=11.9 (1917-23) or 11.1 (1929-34) days
 * (<a href= "https://doi.org/10.1016/j.jtbi.2011.06.015">Klinkenberg, 2011</a>)
 * or incubation between 7-21 days (<a href=
 * "https://wwwnc.cdc.gov/travel/yellowbook/2016/infectious-diseases-related-to-travel/measles-rubeola">CDC</a>)</li>
 * </ul>
 * <p>
 * outbreak in NL between Feb 2013 and May 2014 documented in Osiris DB
 * <p>
 * source: <a href=
 * "https://www.volksgezondheidenzorg.info/onderwerp/vaccinaties/cijfers-context/trends#node-trend-vaccinatiegraad-zuigelingen">
 * volksgezondheidenzorg.info</a>
 * <table style="width:250px;border:1px dashed black;">
 * <caption><em>BMR vaccination trend, infants (cohort 2.y.o)</em></caption>
 * <tbody>
 * <tr>
 * <td>1995</td>
 * <td>96,1</td>
 * </tr>
 * <tr>
 * <td>1996</td>
 * <td>95,8</td>
 * </tr>
 * <tr>
 * <td>1997</td>
 * <td>95,6</td>
 * </tr>
 * <tr>
 * <td>1998</td>
 * <td>95,6</td>
 * </tr>
 * <tr>
 * <td>1999</td>
 * <td>95,4</td>
 * </tr>
 * <tr>
 * <td>2000</td>
 * <td>95,2</td>
 * </tr>
 * <tr>
 * <td>2001</td>
 * <td>95,8</td>
 * </tr>
 * <tr>
 * <td>2002</td>
 * <td>96,3</td>
 * </tr>
 * <tr>
 * <td>2003</td>
 * <td>95,4</td>
 * <td rowSpan=10 vAlign=top align=justify bgcolor=yellow>Vanaf cohort 2003
 * wordt gerapporteerd op basis van het nieuwe informatiesysteem en de
 * vaccinatietoestand op individuele leeftijd.</td>
 * </tr>
 * <tr>
 * <td>2004</td>
 * <td>95,9</td>
 * </tr>
 * <tr>
 * <td>2005</td>
 * <td>96</td>
 * </tr>
 * <tr>
 * <td>2006</td>
 * <td>96,2</td>
 * </tr>
 * <tr>
 * <td>2007</td>
 * <td>96,2</td>
 * </tr>
 * <tr>
 * <td>2008</td>
 * <td>95,9</td>
 * </tr>
 * <tr>
 * <td>2009</td>
 * <td>95,9</td>
 * </tr>
 * <tr>
 * <td>2010</td>
 * <td>96,1</td>
 * </tr>
 * <tr>
 * <td>2011</td>
 * <td>96</td>
 * </tr>
 * <tr>
 * <td>2012</td>
 * <td>95,5</td>
 * </tr>
 * </tbody>
 * </table>
 * 
 * @version $Id$
 * @author Rick van Krevelen
 */
public abstract class Measles implements Pathogen
{

}