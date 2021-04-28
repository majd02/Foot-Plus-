<?php

namespace App\Controller;

use App\Entity\Reservation;
use App\Entity\Stade;
use App\Form\ReservationType;
use App\Form\StadeType;
use App\Repository\StadeRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;


/**
 * @Route("/stade")
 */
class StadeController extends AbstractController
{
    /**
     * @Route("/", name="stade_index", methods={"GET"})
     */
    public function index(StadeRepository $stadeRepository): Response
    {
        return $this->render('stade/index.html.twig', [
            'stades' => $stadeRepository->findAll(),
        ]);
    }

    /**
     * @Route("/new", name="stade_new", methods={"GET","POST"})
     */
    public function new(Request $request): Response
    {
        $stade = new Stade();
        $form = $this->createForm(StadeType::class, $stade);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $file = $stade->getImage();
            $fileName = md5(uniqid().'.'.$file->guessExtension());
            try {
                $file->move(
                    $this->getParameter('images_directory'),
                    $fileName
                );
            } catch (FileException $e) {
                // ... handle exception if something happens during file upload
            }
            $entityManager = $this->getDoctrine()->getManager();
            $stade->setImage($fileName);

            $entityManager->persist($stade);
            $entityManager->flush();

            return $this->redirectToRoute('stade_index');
        }

        return $this->render('stade/new.html.twig', [
            'stade' => $stade,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{idStade}", name="stade_show", methods={"GET","POST"})
     *
     * @param $idStade
     */
    public function show(Stade $stade , Request $request): Response

    {
        $reservation = new Reservation();

        $form = $this->createForm(ReservationType::class, $reservation);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($reservation);
            $entityManager->flush();}
        return $this->render('stade/show.html.twig', [
            'stade' => $stade,
            'form' => $form->createView()
        ]);
    }

    /**
     * @Route("/{idStade}/edit", name="stade_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, Stade $stade): Response
    {
        $form = $this->createForm(StadeType::class, $stade);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $file = $stade->getImage();
            $fileName = md5(uniqid().'.'.$file->guessExtension());
            try {
                $file->move(
                    $this->getParameter('images_directory'),
                    $fileName
                );
            } catch (FileException $e) {
                // ... handle exception if something happens during file upload
            }

            $stade->setImage($fileName);
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('stade_index');
        }

        return $this->render('stade/edit.html.twig', [
            'stade' => $stade,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{idStade}/delete", name="stade_delete", methods={"POST"})
     */
    public function delete(Request $request, Stade $stade): Response
    {
        if ($this->isCsrfTokenValid('delete'.$stade->getIdStade(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($stade);
            $entityManager->flush();
        }

        return $this->redirectToRoute('stade_index');
    }

    /**
     *
     * @Route("/search", name="ajax_search",methods={"GET"})
     *
     */

    public function searchAction(Request $request)
    {

        $em = $this->getDoctrine()->getManager();
        $requestString = $request->get('q');
        $stades=$em->getRepository('App:Stade')->findEntitiesByString($requestString);
        if(!$stades){
            $result['stades']['error']="Not Found :(";
        }
        else {
            $result['stades']=$this->getRealEntities($stades);
        }
        return new Response(json_encode($result));
    }
    public function getRealEntities($stades){
        foreach ($stades as $stades){
            $realEntities[$stades->getIdStade()] = [$stades->getNom(),$stades->getAdresse()];
        }
        return $realEntities;
    }

    /**
     * @Route("/TrierParPrix", name="TrierParPrix")
     */
    public function TrierParPrix(Request $request): Response
    {
        $repository = $this->getDoctrine()->getRepository(Stade::class);
        $stades = $repository->findByPrix();

        return $this->render('stade/index.html.twig', [
            'stades' => $stades,
        ]);
    }


}
