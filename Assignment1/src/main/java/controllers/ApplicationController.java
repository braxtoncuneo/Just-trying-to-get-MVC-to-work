/**
 * Copyright (C) 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package controllers;

import models.gameState;
import ninja.Context;
import ninja.Result;
import ninja.Results;

import com.google.inject.Singleton;


@Singleton
public class ApplicationController {

    public Result acesUp() {
        return Results.html().template("views/AcesUp/AcesUp.flt.html");
    }

    public Result acesUpGamestateGet(){
        gameState gs = new gameState();

        return Results.json().render(gs);
    }

    public Result acesUpGamestatePost(Context context, gameState gs) {
        if(gs.action.contentEquals("deal")){
            gs.deal();
        }
        if(gs.action.contentEquals("discard")){
            gs.discard(gs.item1);
        }
        if(gs.action.contentEquals("move")){
            gs.move(gs.item2,gs.item3);
        }

        return Results.json().render(gs);
    }

    public Result index() {
        return Results.html();
    }

}
