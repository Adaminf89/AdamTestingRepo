//
//  PersonaClass.swift
//  AdamInfiesto_CE05
//
//  Created by Adam Infiesto on 10/17/17.
//  Copyright © 2017 Adam Infiesto. All rights reserved.
//

import Foundation
import UIKit

class Persona
{
    var name : String
    var persona : [Characterz]
    
    func getterSpot(index: Int) -> Characterz
    {
        return persona[index]
    }
    
    //need for deleting and updating after pressed
    func getCount()-> Int
    {
        return self.persona.count
    }
    
    func addPersona(daPersona: Characterz)
    {
        //add selected persona's to the class of persona 
        persona += [daPersona]
    }
    
    
    init(name : String, persona: [Characterz] = [Characterz]())
    {
        self.name = name
        self.persona = persona
        
    }
}
